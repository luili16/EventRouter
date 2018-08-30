package com.llx.eventrouter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.llx.eventrouter.enty.SubscriberParamType;
import com.llx.eventrouter.enty.SubscriberReturnType;
import com.llx.eventrouter.enty.ValueWrapper;

public class RemoteService2 extends Service {

    private static final String TAG = "RemoteService2";

    private ValueWrapper mValueWrapper = new ValueWrapper();
    private SubscriberParamType sbt = new SubscriberParamType(mValueWrapper);
    private SubscriberReturnType srt = new SubscriberReturnType();
    private IDebugBridge.Stub mDebugBridge = new IDebugBridge.Stub() {
        @Override
        public ValueWrapper getVal() throws RemoteException {
            mValueWrapper.setReady("ready");
            return mValueWrapper;
        }

        @Override
        public void setCmd(String cmd) throws RemoteException {
            switch (cmd) {
                case CMD.REMOTE_SEND:
                    try {
                        Log.d("main","post hello world!!! finish");
                        EventRouter.get().post("hello_world","remote_send");
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("main","onCrate ---------------------------");
        EventRouter.init(this,getPackageName());
        try {
            EventRouter.get().register(sbt);
            EventRouter.get().register(srt);
        } catch (UnSupportParameterException e) {
            Log.e(TAG,"",e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventRouter.get().unRegister(sbt);
        EventRouter.get().unRegister(srt);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mDebugBridge;
    }
}
