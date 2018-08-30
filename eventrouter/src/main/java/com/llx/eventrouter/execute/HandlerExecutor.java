package com.llx.eventrouter.execute;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;


public class HandlerExecutor implements Executor {

    private static final String TAG = "HandlerExecutor";

    private Handler mHandler;
    private HandlerThread mThread;

    HandlerExecutor() {
        mThread = new HandlerThread("EventRouterHandlerThread");
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
    }

    @Override
    public void execute(final Method method, final Object paramObj, final Object obj) throws Exception {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (paramObj == null) {
                        method.invoke(obj);
                    } else {
                        method.invoke(obj, paramObj);
                    }
                } catch (IllegalAccessException e) {
                    Log.e(TAG,"never happen");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void execute(Runnable r) {
        mHandler.post(r);
    }

    @Override
    public Object submit(Method method, Object paramObj, Object obj) throws Exception {
        CountDownLatch doneSignal = new CountDownLatch(1);
        SyncRunner runner = new SyncRunner(doneSignal,method,paramObj,obj);
        mHandler.post(runner);
        doneSignal.await();
        if (runner.mException != null) {
            throw new RuntimeException(runner.mException);
        }
        return runner.mReturnValue;
    }

    @Override
    public void quit() {
        mThread.quit();
        mThread = null;
    }
}
