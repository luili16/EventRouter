package com.llx.eventrouter.execute;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PoolExecutor implements Executor {
    private static final String TAG = "PoolExecutor";
    private ExecutorService mExecutor;

    PoolExecutor() {
        mExecutor = ThreadPoolProvider.provide();
    }

    @Override
    public void execute(final Method method, final Object paramObj, final Object obj) throws Exception {
        mExecutor.execute(new Runnable() {
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
    public void execute(Runnable r) {
        mExecutor.execute(r);
    }

    @Override
    public Object submit(final Method method, final Object paramObj, final Object obj) throws Exception {

        Future<Object> submit = mExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                Object result;
                if (paramObj == null) {
                    result = method.invoke(obj);
                } else {
                    result = method.invoke(obj, paramObj);
                }
                return result;
            }
        });
        return submit.get();
    }

    @Override
    public void quit() {
        mExecutor.shutdown();
    }
}
