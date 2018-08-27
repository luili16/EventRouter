package com.llx.eventrouter;

import android.support.annotation.Nullable;

import com.llx.eventrouter.execute.ThreadModel;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class Subscription {

    private WeakReference<Object> subscribeRef;
    private ThreadModel threadModel;
    private Type type;
    private Method method;

    public Subscription(Object obj, ThreadModel threadModel, Type type, Method method) {
        this.subscribeRef = new WeakReference<>(obj);
        this.threadModel = threadModel;
        this.type = type;
        this.method = method;
    }

    @Nullable
    public Object getSubscribeRef() {
        return subscribeRef.get();
    }

    public ThreadModel getThreadModel() {
        return threadModel;
    }

    public Type getType() {
        return type;
    }

    public Method getMethod() {
        return method;
    }
}
