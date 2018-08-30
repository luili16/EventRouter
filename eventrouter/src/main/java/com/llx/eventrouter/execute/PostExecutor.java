package com.llx.eventrouter.execute;

import java.lang.reflect.Method;

public class PostExecutor implements Executor {

    PostExecutor() {
    }

    @Override
    public void execute(Method method, Object paramObj, Object obj) throws Exception {
        if (paramObj == null) {
            method.invoke(obj);
        } else {
            method.invoke(obj, paramObj);
        }
    }

    @Override
    public void execute(Runnable r) {
        r.run();
    }

    @Override
    public Object submit(Method method, Object paramObj, Object obj) throws Exception {
        if (paramObj == null) {
            return method.invoke(obj);
        } else {
            return method.invoke(obj, paramObj);
        }
    }

    @Override
    public void quit() {
    }
}
