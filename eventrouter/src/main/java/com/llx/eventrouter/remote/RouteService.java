package com.llx.eventrouter.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.llx.eventrouter.Const.TAG;

/**
 * 此服务用来中转进程与进程间的数据交互
 *
 * 注意，此服务只能运行在一个独立的进程中，也就是说在AndroidManifest.xml中这个Service要声明为一个
 * 独立的进程，如果不这样，则会导致其他的进程无法向此进程发送发送消息，从而导致莫名奇妙的bug。
 */
public class RouteService extends Service {

    private class RouterImpl extends IRouter.Stub {

        private Map<Address,IReceiver> mReceiverMap = new ConcurrentHashMap<>();

        @Override
        public void send(String fromAddress,List<String> toAddressStr, final Bundle msg) throws RemoteException {

            if (toAddressStr == null) {
                return;
            }

            for (String a : toAddressStr) {

                final Address toAddress = Address.toAddress(a);

                if (toAddress == null) {
                    throw new RemoteException("unknown address : " + a);
                }

                IReceiver receiver = mReceiverMap.get(toAddress);

                if (receiver == null || !receiver.asBinder().pingBinder()) {
                    Log.e(TAG,String.format("address(%s) lost connect send failed",toAddress.toString()));
                    return;
                }
                receiver.onMessageReceive(fromAddress,msg);
            }
        }

        @Override
        public void addReceiver(IReceiver receiver) throws RemoteException {
            Address fromAddress = Address.toAddress(Binder.getCallingPid());
            mReceiverMap.put(fromAddress,receiver);
        }

        @Override
        public void removeReceiver() throws RemoteException {
            Address fromAddress = Address.toAddress(Binder.getCallingPid());
            mReceiverMap.remove(fromAddress);
        }

        @Override
        public List<String> getAliveClient() throws RemoteException {

            List<String> addressList = new ArrayList<>();
            Set<Map.Entry<Address, IReceiver>> entries = mReceiverMap.entrySet();

            Iterator<Map.Entry<Address, IReceiver>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Address, IReceiver> entry = iterator.next();

                Address address = entry.getKey();
                IBinder binder = entry.getValue().asBinder();
                if (binder == null || !binder.pingBinder()) {
                    Log.e(TAG, String.format("RouterService :  connect lost remote address(%s)",address.toString()));
                    iterator.remove();
                }
                addressList.add(address.toString());
            }

            return addressList;
        }
    }

    private RouterImpl mRouterImpl = new RouterImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return mRouterImpl;
    }
}
