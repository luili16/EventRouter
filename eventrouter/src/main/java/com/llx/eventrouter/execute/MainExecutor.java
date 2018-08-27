package com.llx.eventrouter.execute;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MainExecutor implements Executor {

    private static final String TAG = "MainExecutor";

    private Handler mHandler = new Handler(Looper.getMainLooper());

    MainExecutor() {}

    @Override
    public void execute(final Method method, final Object paramObj, final Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (paramObj == null) {
                        method.invoke(obj);
                    } else {
                        method.invoke(obj,paramObj);
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
    public Object submit(final Method method, final Object paramObj, final Object obj) {
        FutureTask<Object> task = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                if (paramObj == null) {
                    return method.invoke(obj);
                } else {
                    return method.invoke(obj,paramObj);
                }
            }
        });
        task.run();
        Object result;
        try {
            result = task.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void quit() {
    }
}
