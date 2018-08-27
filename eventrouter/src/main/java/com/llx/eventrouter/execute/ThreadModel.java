package com.llx.eventrouter.execute;

/**
 * 声明了订阅事件被执行时在哪个线程运行
 */
public enum ThreadModel {
    /**
     * 主线程执行
     */
    MAIN,
    /**
     * 在发布订阅事件的那个线程中执行
     */
    POST,
    /**
     * 在一个自定义的线程中执行
     */
    POOL,
    /**
     * 在handlerThread线程中执行
     */
    HANDLER
}
