package com.llx.eventrouter.execute;

import java.lang.reflect.Method;

public interface Executor {

    /**
     * 执行一个指定的function
     */
    void execute(Method method, Object paramObj, Object obj) throws Exception;

    /**
     * 执行一个Runnable
     * @param r Runnable
     */
    void execute(Runnable r);

    /**
     * 执行一个指定的function，并将执行的结果返回
     * 注意： 这个方法会阻塞当前的线程，直到待执行的方法执行结束返回执行的结果，如果待执行的
     * 方法崩溃了，那么就返回null
     */
    Object submit(Method method, Object paramObj, Object obj) throws Exception;

    /**
     * 清除当前Executor所持有的线程资源
     */
    void quit();
}
