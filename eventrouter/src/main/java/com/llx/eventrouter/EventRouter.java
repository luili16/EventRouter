package com.llx.eventrouter;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.llx.eventrouter.remote.Address;
import com.llx.eventrouter.remote.IReceiver;
import com.llx.eventrouter.remote.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EventRouter {

    private static final String TAG = "EventRouter";

    /**
     * 此key代表了每一个消息的类型
     */
    private static final String KEY_TYPE = "type";

    /**
     * 此value代表向其他进程查询已经注册的订阅事件
     */
    private static final String TYPE_VALUE_OF_QUERY = "query_event";

    /**
     * 此value代表查询已经注册的订阅事件的结果
     */
    private static final String TYPE_VALUE_OF_QUERY_RESULT = "query_event_result";

    /**
     * 向其他进程的总线上发布一个订阅事件
     */
    private static final String TYPE_VALUE_OF_PUBLISH = "publish_event";

    /**
     * 向其他进程的总线上发布此订阅事件执行后的返回值
     */
    private static final String TYPE_VALUE_OF_PUBLISH_RETURN_VALUE = "publish_event_value";

    /**
     * 此key封装了查询订阅事件的列表
     */
    private static final String KEY_QUERY_LIST = "query_list";

    /**
     * 此key封装了发布事件的参数
     */
    private static final String KEY_EVENT_OBJ = "publish_event_obj";

    /**
     * 此key封装了发布事件的tag
     */
    private static final String KEY_TAG = "publish_tag";

    /**
     * 此key分装了发布事件的返回值类型
     */
    private static final String KEY_RETURN_CLASS_NAME = "publish_return_class_name";

    /**
     * 此key封装了发布事件执行完毕后的返回值
     */
    private static final String KEY_RETURN_VALUE = "return_value";

    /**
     * 此key封装了每个消息唯一标志
     */
    private static final String KEY_ID = "router_id";

    //ms
    private static final long DEFAULT_TIMEOUT = 2000;

    private static EventRouter sEventRouter;

    public static void init(Context context, String pkg) {
        if (sEventRouter != null) {
            throw new RuntimeException("EventRouter can init only once");
        }
        sEventRouter = new EventRouter(context, pkg);
    }

    public static void destroy(Context context) {
        sEventRouter.mTransport.destroy(context);
    }

    public static EventRouter get() {

        if (sEventRouter == null) {
            throw new RuntimeException("EventRouter should be init before use");
        }

        return sEventRouter;
    }

    private EventRouterLocal mLocal;
    private Transport mTransport;

    private ArrayList<MessageObserver> mHandlers = new ArrayList<>();

    /**
     * 保存了等待其他进程执行结束返回的结果的列表
     */
    private ConcurrentHashMap<String, Publisher> mWaiter = new ConcurrentHashMap<>();

    /**
     * 保存了每个消息所对一个的那一时刻的事件列表
     * <p>
     * 我没有考虑缓存所有进程的事件列表的原因是每个进程随时都有可能被杀死，这样的缓存列表很难维护，到不如
     * 每次都做一次查询，这样虽然效率会低点，但能避免很多bug。
     */
    private ConcurrentHashMap<String, EventListHolder> mSubsEventsSnapshot = new ConcurrentHashMap<>();

    private EventRouter(Context context, String pkg) {
        mLocal = new EventRouterLocal();
        mTransport = new Transport(context, pkg);
        try {
            mTransport.addReceiver(new IReceiver() {
                @Override
                public void onMessageReceive(String fromAddress, Bundle message) {
                    if (!TextUtils.isEmpty(fromAddress) && message != null) {
                        message.setClassLoader(EventRouter.class.getClassLoader());
                        for (MessageObserver handler : mHandlers) {
                            if (handler.handleMessage(fromAddress,message)) {
                                return;
                            }
                        }
                    }
                }

                @Override
                public IBinder asBinder() {
                    return null;
                }
            });
        } catch (RemoteException e) {
            Log.e(TAG,"",e);
        }
        mHandlers.add(new ResultHandler());
        mHandlers.add(new ExecuteHandler());
        mHandlers.add(new QueryHandler());
        mHandlers.add(new QueryResultHandler());

    }

    public void register(Object subscriber) {
        mLocal.register(subscriber);
    }

    public void unRegister(Object subscriber) {
        mLocal.unRegister(subscriber);
    }

    /**
     * 发布订阅事件
     *
     * @param objParam    订阅方法参数
     * @param tag         订阅方法标志
     * @param returnClass 返回值class
     * @param timeout     超时时间
     * @param addresses   发送地址，如果为空，则对所有已经连接的进程去寻找匹配的事件发送
     * @return 执行的结果
     */
    public Object post(Object objParam, String tag, Class<?> returnClass, long timeout,
                       Address... addresses) throws TimeoutException {
        List<String> toAddressList;
        if (addresses == null || addresses.length == 0) {
            // 从进程列表中查询出所有符合的事件并执行
            toAddressList = prepare(objParam, tag, returnClass, timeout);
            if (toAddressList.isEmpty()) {
                return null;
            }
        } else {
            toAddressList = new ArrayList<>();
            for (Address address : addresses) {
                toAddressList.add(address.toString());
            }
        }

        if (returnClass.equals(void.class)) {
            for (String toAddress : toAddressList) {
                Publisher publisher = new Publisher(toAddress);
                publisher.publish(objParam, tag, void.class.getCanonicalName(), timeout);
            }
            return null;
        } else {
            String toAddress = toAddressList.get(0);
            Publisher publisher = new Publisher(toAddress);
            return publisher.publish(objParam, tag, returnClass.getCanonicalName(), timeout);
        }
    }

    private ArrayList<String> prepare(Object objParam, String tag, Class<?> returnCls,
                                      long timeout) throws TimeoutException {
        ArrayList<String> toAddressList = new ArrayList<>();
        try {
            List<String> aliveClients = mTransport.getAliveClient();
            if (aliveClients.isEmpty()) {
                return toAddressList;
            }
            CountDownLatch signal = new CountDownLatch(aliveClients.size());
            String id = UUID.randomUUID().toString();
            mSubsEventsSnapshot.put(id, new EventListHolder(signal));

            // 发送查询事件的消息
            Bundle msg = new Bundle(getClass().getClassLoader());
            msg.putString(KEY_ID, id);
            msg.putString(KEY_TYPE, TYPE_VALUE_OF_QUERY);

            Address broadcast = Address.toBroadcast();
            mTransport.send(broadcast.toString(), msg);

            // 等待查询结束
            if (!signal.await(timeout, TimeUnit.MILLISECONDS)) {
                mSubsEventsSnapshot.remove(id);
                throw new TimeoutException("query event timeout");
            }
            String paramType;
            if (objParam == null) {
                paramType = void.class.getCanonicalName();
            } else {
                paramType = objParam.getClass().getCanonicalName();
            }
            Event event = new Event(paramType, tag, returnCls.getCanonicalName());
            EventListHolder holder = mSubsEventsSnapshot.get(id);
            if (holder == null) {
                return toAddressList;
            }

            for (Map.Entry<String, ArrayList<Event>> entry : holder.mEventMap.entrySet()) {
                String address = entry.getKey();
                ArrayList<Event> events = entry.getValue();
                for (Event remoteEvent : events) {
                    if (remoteEvent.equals(event)) {
                        toAddressList.add(address);
                    }
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "unexpected error", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "unexpected error", e);
        }
        return toAddressList;
    }

    public Object post(Object objParam, String tag, Class<?> returnClass) throws TimeoutException {
        return post(objParam, tag, returnClass, DEFAULT_TIMEOUT);
    }

    public boolean ping(Address address) {

        return false;
    }

    private class EventListHolder {
        ConcurrentHashMap<String, ArrayList<Event>> mEventMap = new ConcurrentHashMap<>();
        CountDownLatch mSignal;

        EventListHolder(CountDownLatch signal) {
            mSignal = signal;
        }
    }

    private class Publisher {
        final String mAddress;
        final String mId = UUID.randomUUID().toString();
        Object mResult;
        final CountDownLatch mSignal;

        Publisher(String address) {
            this.mAddress = address;
            this.mSignal = new CountDownLatch(1);
        }

        Object publish(Object paramObj, String tag, String returnType, long timeout) throws TimeoutException {

            // 封装消息
            Bundle msg = new Bundle(getClass().getClassLoader());
            msg.putString(KEY_TYPE, TYPE_VALUE_OF_PUBLISH);
            msg.putString(KEY_ID, this.mId);

            if (paramObj != null) {
                convertType(paramObj, msg, KEY_EVENT_OBJ);
            }

            msg.putString(KEY_TAG, tag);
            msg.putString(KEY_RETURN_CLASS_NAME, returnType);

            if (returnType.equals(void.class.getCanonicalName())) {
                // 返回值为空，直接返回
                try {
                    mTransport.send(mAddress, msg);
                } catch (RemoteException e) {
                    Log.e(TAG, "send msg to " + mAddress + " failed", e);
                }
            } else {
                // 其他类型需要缓存执行的时间，等待执行结果的返回
                mWaiter.put(mId, this);
                try {
                    mTransport.send(mAddress, msg);
                    if (!mSignal.await(timeout, TimeUnit.MILLISECONDS)) {
                        throw new TimeoutException("wait result from remote process timeout");
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                    return null;
                } catch (InterruptedException e) {
                    Log.e(TAG, "", e);
                    return null;
                } finally {
                    mWaiter.remove(mId);
                }
            }
            return mResult;
        }
    }

    /**
     * 消息处理接口
     */
    private interface MessageObserver {
        /**
         * 处理一个到达的消息
         *
         * @param fromAddress 地址
         * @param message     消息
         * @return true 此条消息已经被处理 false 没有处理
         */
        boolean handleMessage(String fromAddress, Bundle message);
    }

    // --------------------- MessageObserver的不同实现 -------------------------

    /**
     * 用来处理查询注册列表的类
     */
    private class QueryHandler implements MessageObserver {
        @Override
        public boolean handleMessage(String fromAddress, Bundle message) {
            String typeValue = message.getString(KEY_TYPE);
            if (TYPE_VALUE_OF_QUERY.equals(typeValue)) {
                String id = message.getString(KEY_ID);
                Bundle valueMsg = new Bundle(getClass().getClassLoader());
                ArrayList<Event> events = new ArrayList<>(mLocal.queryEvent());
                valueMsg.putString(KEY_ID, id);
                valueMsg.putString(KEY_TYPE, TYPE_VALUE_OF_QUERY_RESULT);
                valueMsg.putParcelableArrayList(KEY_QUERY_LIST, events);
                try {
                    mTransport.send(fromAddress, valueMsg);
                } catch (RemoteException e) {
                    Log.e(TAG, String.format("send query event list to %s failed!", fromAddress), e);
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 用来处理查询订阅注册列表的结果类
     */
    private class QueryResultHandler implements MessageObserver {

        @Override
        public boolean handleMessage(String fromAddress, Bundle message) {

            String typeValue = message.getString(KEY_TYPE);
            if (TYPE_VALUE_OF_QUERY_RESULT.equals(typeValue)) {

                ArrayList<Event> events = message.getParcelableArrayList(KEY_QUERY_LIST);
                if (events == null) {
                    events = new ArrayList<>();
                }
                String id = message.getString(KEY_ID);
                EventListHolder holder = mSubsEventsSnapshot.get(id);
                if (holder != null) {
                    holder.mEventMap.put(fromAddress, events);
                    holder.mSignal.countDown();
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 执行发布事件
     */
    private class ExecuteHandler implements MessageObserver {

        @Override
        public boolean handleMessage(String fromAddress, Bundle message) {
            String typeValue = message.getString(KEY_TYPE);
            if (TYPE_VALUE_OF_PUBLISH.equals(typeValue)) {
                // 执行一个发布事件
                String id = message.getString(KEY_ID);
                Object paramObj = message.get(KEY_EVENT_OBJ);
                String tag = message.getString(KEY_TAG);
                String returnType = message.getString(KEY_RETURN_CLASS_NAME);
                Object returnValue = mLocal.post(paramObj, tag, returnType);
                // 只有返回值是非空才会发送回去
                if (!TextUtils.isEmpty(returnType) && !returnType.equals(void.class.getCanonicalName())) {
                    Bundle msg = new Bundle(getClass().getClassLoader());
                    msg.putString(KEY_TYPE, TYPE_VALUE_OF_PUBLISH_RETURN_VALUE);
                    if (returnValue != null) {
                        convertType(returnValue, msg, KEY_RETURN_VALUE);
                    }
                    msg.putString(KEY_ID, id);
                    try {
                        mTransport.send(fromAddress, msg);
                    } catch (RemoteException e) {
                        Log.e(TAG, "send msg to " + fromAddress + " failed", e);
                    }
                }
            }
            return false;
        }
    }

    /**
     * 等待返回结果
     */
    private class ResultHandler implements MessageObserver {
        @Override
        public boolean handleMessage(String fromAddress, Bundle message) {

            String typeValue = message.getString(KEY_TYPE);
            if (TYPE_VALUE_OF_PUBLISH_RETURN_VALUE.equals(typeValue)) {
                Object returnValue = message.get(KEY_RETURN_VALUE);
                String id = message.getString(KEY_ID);
                if (!TextUtils.isEmpty(id)) {
                    Publisher publisher = mWaiter.get(id);
                    if (publisher != null) {
                        publisher.mResult = returnValue;
                        publisher.mSignal.countDown();
                    }
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 判断合适的类型，并放入message
     *
     * @param value 带匹配的vaue
     * @param msg   bundle
     * @param key   key
     */
    private static void convertType(Object value, Bundle msg, String key) {
        if (value instanceof Integer) {
            msg.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            msg.putLong(key, (Long) value);
        } else if (value instanceof Byte) {
            msg.putByte(key, (Byte) value);
        } else if (value instanceof String) {
            msg.putString(key, (String) value);
        } else if (value instanceof ArrayList) {
            msg.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
        } else if (value instanceof Parcelable) {
            msg.putParcelable(key, (Parcelable) value);
        }
    }
}
