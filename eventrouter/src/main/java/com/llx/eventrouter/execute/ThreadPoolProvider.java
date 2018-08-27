package com.llx.eventrouter.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 子类实现这个类来提供自定义的线程池实现
 *
 */
public abstract class ThreadPoolProvider {

    private static ThreadPoolProvider sProvider;


    public static void takeFrom(ThreadPoolProvider injector) {
        if (injector == null) {
            throw new IllegalStateException("provider is not null! you have injected a provider");
        }
        sProvider = injector;

    }

    public static ExecutorService provide() {
        if (sProvider != null) {
            return sProvider.make();
        }
        return Executors.newFixedThreadPool(2);
    }
    abstract ExecutorService make();
}
