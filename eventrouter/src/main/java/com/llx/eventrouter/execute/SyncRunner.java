package com.llx.eventrouter.execute;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

class SyncRunner implements Runnable {

    private CountDownLatch mDoneSignal;
    private Method mMethod;
    private Object mParamObj;
    private Object mObj;

    public SyncRunner(CountDownLatch doneSignal, Method method, Object paramObj, Object obj) {
        this.mDoneSignal = doneSignal;
        this.mMethod = method;
        this.mParamObj = paramObj;
        this.mObj = obj;
    }

    Object mReturnValue;
    Exception mException = null;


    @Override
    public void run() {
        try {
            if (mParamObj == null) {
                mReturnValue = mMethod.invoke(mObj);
            } else {
                mReturnValue = mMethod.invoke(mObj,mParamObj);
            }
        } catch (Exception e) {
            mException = e;
        }finally {
            mDoneSignal.countDown();
        }
    }
}
