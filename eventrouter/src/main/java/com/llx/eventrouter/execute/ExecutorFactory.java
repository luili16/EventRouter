package com.llx.eventrouter.execute;

public class ExecutorFactory {

    public static Executor create(ThreadModel model) {
        switch (model) {
            case MAIN:
                return new MainExecutor();
            case POOL:

                return new PoolExecutor();
            case POST:
                return new PostExecutor();
            case HANDLER:
                return new HandlerExecutor();
        }
        throw new RuntimeException("unknow thread model");
    }
}
