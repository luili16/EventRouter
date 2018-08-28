package com.llx.eventrouter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.llx.eventrouter.enty.SubscriberParamType;
import com.llx.eventrouter.enty.ValueWrapper;

public class RemoteService extends Service {

    private static final String TAG = "RemoteService";

    private ValueWrapper mValueWrapper = new ValueWrapper();
    private SubscriberParamType sbt = new SubscriberParamType(mValueWrapper);
    private IDebugBridge.Stub mDebugBridge = new IDebugBridge.Stub() {
        @Override
        public ValueWrapper getVal() throws RemoteException {
            mValueWrapper.setReady("ready");
            return mValueWrapper;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        EventRouter.init(this,getPackageName());
        try {
            EventRouter.get().register(sbt);
        } catch (UnSupportParameterException e) {
            Log.e(TAG,"",e);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mDebugBridge;
    }
}
