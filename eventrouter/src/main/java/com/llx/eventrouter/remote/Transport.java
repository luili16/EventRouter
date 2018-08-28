package com.llx.eventrouter.remote;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Transport implements IRouter {

    private static final String TAG = "Transport";
    private IRouter mRouter;
    private IReceiver mOutReceiver;
    private RouterConn mConn;

    public Transport(Context context, String pkg) {
        Intent routerIntent = new Intent();
        String cls = "com.llx.eventrouter.remote.RouteService";
        routerIntent.setComponent(new ComponentName(pkg, cls));
        mConn = new RouterConn();
        if (!context.bindService(routerIntent, mConn, Context.BIND_AUTO_CREATE)) {
            Log.e(TAG,"bind service : " + cls + "failed! current package : " + pkg);
        }
    }

    public void destroy(Context context) {
        if (mRouter != null) {
            try {
                mRouter.removeReceiver();
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        }
        if (mConn != null) {
            context.unbindService(mConn);
        }
        Log.i(TAG, "Transport : have disconnect with RouteService and current process is " + Process.myPid());
    }

    @Override
    public void send(String address, Bundle msg) throws RemoteException {
        if (mRouter != null) {
            mRouter.send(address, msg);
        }
    }

    @Override
    public void addReceiver(IReceiver receiver) throws RemoteException {
        if (receiver == null) {
            Log.w(TAG, "passing an empty receiver when call addReceiver(IReceiver) method");
            return;
        }

        mOutReceiver = receiver;
    }

    @Override
    public void removeReceiver() throws RemoteException {
        if (mRouter != null) {
            mRouter.removeReceiver();
        }
    }

    @Override
    public List<String> getAliveClient() throws RemoteException {
        if (mRouter != null) {
            return mRouter.getAliveClient();
        }
        return new ArrayList<>();
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    private class ReceiverImpl extends IReceiver.Stub {

        @Override
        public void onMessageReceive(String where, Bundle message) throws RemoteException {
            if (mOutReceiver != null) {
                mOutReceiver.onMessageReceive(where, message);
            }
        }
    }

    private class RouterConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRouter = IRouter.Stub.asInterface(service);
            Log.d(TAG,"Router Service connected");
            if (mRouter != null) {
                try {
                    mRouter.addReceiver(new ReceiverImpl());
                } catch (RemoteException e) {
                    Log.e(TAG, "add receiver failed!", e);
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRouter = null;
        }
    }
}
