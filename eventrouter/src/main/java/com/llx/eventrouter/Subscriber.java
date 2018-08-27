package com.llx.eventrouter;

import com.llx.eventrouter.execute.ThreadModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscriber {

    /**
     * 如何执行这个订阅事件
     *
     * 注意: 如果订阅方法的返回值不是void，那么Type类型一定是BLOCK_RETURN,因为只有这样才能在订阅方法
     * 执行完成之后将得到的返回值返回给调用者。
     */
    Type type() default Type.DEFAULT;

    /**
     * 订阅事件的标志
     */
    String tag();

    /**
     * 订阅的方法在那个线程执行，默认是在主线程
     */
    ThreadModel threadModel() default ThreadModel.MAIN;

    /**
     * true 则意味着这个事件可以跨进程发布
     */
    boolean remote() default false;


}
