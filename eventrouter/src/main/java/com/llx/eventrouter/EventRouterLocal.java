package com.llx.eventrouter;

import android.os.Parcelable;
import android.util.Log;

import com.llx.eventrouter.execute.Executor;
import com.llx.eventrouter.execute.ExecutorFactory;
import com.llx.eventrouter.execute.ThreadModel;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

class EventRouterLocal {

    private static final String TAG = "EventRouterLocal";

    private Map<Event, CopyOnWriteArrayList<Subscription>> mSubscribeMap = new ConcurrentHashMap<>();

    EventRouterLocal() {}

    /**
     * 订阅一个事件
     * <p>
     * 注意： 同一个订阅事件不要重复注册，这样会导致一个订阅事件会被执行多次
     *
     * @throws IllegalStateException 当订阅方法的返回值不为空，并且type=Type.DEFAULT的时候会抛出此异常，因为
     *                               当一个订阅方法有返回值的时候则需要将返回值返回去，这意味着需要阻塞当前的线程
     */
    public void register(Object subscriber) {
        if (subscriber == null) {
            return;
        }

        Class<?> sClass = subscriber.getClass();
        Method[] methods = sClass.getDeclaredMethods();
        for (Method method : methods) {
            Subscriber annotation = method.getAnnotation(Subscriber.class);
            if (annotation != null) {
                Class<?>[] pTypes = method.getParameterTypes();
                // 只允许订阅的方法参数为空或一个参数
                if (pTypes != null && pTypes.length <= 1) {
                    Class<?> pType;
                    if (pTypes.length == 1) {
                        pType = pTypes[0];
                        checkParcelable(pType);
                    } else {
                        pType = void.class;
                    }
                    Class<?> returnType = method.getReturnType();
                    checkParcelable(returnType);
                    Event event = new Event(pType.getCanonicalName(), annotation.tag(),
                            returnType.getCanonicalName());
                    CopyOnWriteArrayList<Subscription> sList = mSubscribeMap.get(event);
                    if (sList == null) {
                        sList = new CopyOnWriteArrayList<>();
                    }
                    Type type = annotation.type();
                    ThreadModel threadModel = annotation.threadModel();
                    Subscription s = new Subscription(subscriber, threadModel, type, method);
                    sList.add(s);
                    // 这里约定如果一个订阅方法有一个返回值的话，那么此方法只能存在一个
                    // 这样是为了避免产生歧义
                    if (!returnType.equals(void.class)) {
                        if (sList.size() > 1 || !annotation.type().equals(Type.BLOCK)) {
                            throw new IllegalStateException("more than one subscription or wrong Type");
                        }
                    }
                    mSubscribeMap.put(event, sList);
                }
            }
        }
    }

    private static void checkParcelable(Class<?> aClass) {
        Class<?>[] ifs = aClass.getInterfaces();
        if (ifs != null && ifs.length > 0) {
            for (Class<?> c : ifs) {
                if (c.equals(Parcelable.class)) {
                    //implement
                    return;
                }
            }
        }
        // not implement
        throw new IllegalStateException("param must implement parcelable");
    }

    private boolean isOriginalType(Class<?> eventType) {
        return false;
    }

    public void unRegister(Object subscriber) {
        if (subscriber == null) {
            return;
        }
        Set<Map.Entry<Event, CopyOnWriteArrayList<Subscription>>> entries = mSubscribeMap.entrySet();
        Iterator<Map.Entry<Event, CopyOnWriteArrayList<Subscription>>> entriesIterator = entries.iterator();
        while (entriesIterator.hasNext()) {
            Map.Entry<Event, CopyOnWriteArrayList<Subscription>> entry = entriesIterator.next();
            CopyOnWriteArrayList<Subscription> subscriptionList = entry.getValue();
            if (subscriptionList != null) {
                for (Subscription subscription : subscriptionList) {
                    Object cachedSubscribe = subscription.getSubscribeRef();
                    if (cachedSubscribe == null) {
                        subscriptionList.remove(subscription);
                    } else if (cachedSubscribe.equals(subscriber)) {
                        subscriptionList.remove(subscription);
                    }
                }
                if (subscriptionList.isEmpty()) {
                    entriesIterator.remove();
                }
            }
        }
    }


    /**
     * 发布订阅事件
     *
     * @param paramObj        订阅方法的参数
     * @param tag        订阅方法的标志
     * @param returnType 订阅方法的返回值类型
     */
    Object post(Object paramObj, String tag, String returnType) {

        String paramType;
        if (paramObj == null) {
            paramType = void.class.getCanonicalName();
        } else {
            paramType = paramObj.getClass().getCanonicalName();
        }

        Event event = new Event(paramType,tag,returnType);
        CopyOnWriteArrayList<Subscription> subscriptions = mSubscribeMap.get(event);
        if (subscriptions == null) {
            Log.e(TAG,String.format("EventBus : cannot find %s from subscribedMap",event.toString()));
            return null;
        }

        for (Subscription subs: subscriptions) {
            Executor executor = ExecutorFactory.create(subs.getThreadModel());
            Object subscribe = subs.getSubscribeRef();
            if (subscribe == null) {
                Log.e(TAG,String.format("subscriber obj maybe recycled by GC,execute failed! currentEvent is %s",event.toString()));
                continue;
            }
            Method method = subs.getMethod();
            try {
                if (subs.getType() == Type.BLOCK) {
                    // 因为返回值只能有一个，所以默认只是第一个注册的有效
                    return executor.submit(method,paramObj,subscribe);
                } else if (subs.getType() == Type.DEFAULT) {
                    executor.execute(method,paramObj,subscribe);
                }
            } catch (Exception e) {
                Log.e(TAG,String.format("execute register method failed! method name is %s",method.getName()));
                return null;
            }
        }
        return null;
    }

    Set<Event> queryEvent() {
        return mSubscribeMap.keySet();
    }


    /**
     * 发布订阅事件
     * 如果没有返回值可以直接用此方法
     * @param obj 订阅方法的参数
     * @param tag 订阅方法的标志
     */
    void post(Object obj, String tag) {
        //noinspection ResultOfMethodCallIgnored
        post(obj, tag, void.class.getCanonicalName());
    }
}
