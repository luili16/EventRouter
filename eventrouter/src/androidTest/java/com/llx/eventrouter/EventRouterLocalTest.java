package com.llx.eventrouter;

import android.support.test.runner.AndroidJUnit4;

import com.llx.eventrouter.enty.SubscriberBaseType;
import com.llx.eventrouter.enty.SubscriberBaseTypeMirror;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EventRouterLocalTest {


    @Before
    public void before() {


    }

    @After
    public void after() {


    }



    @Test
    public void registerTest() {
        SubscriberBaseType sbt = new SubscriberBaseType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        int count = erl.getSubscribeMap().size();
        assertEquals(13,count);
        erl.unRegister(sbt);
        count = erl.getSubscribeMap().size();
        assertEquals(0,count);
    }

    /**
     * 测试多个类中注册到了同一个事件上
     */
    @Test
    public void registerTestDuple() {
        SubscriberBaseType sbt = new SubscriberBaseType();
        SubscriberBaseTypeMirror sbtm = new SubscriberBaseTypeMirror();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);
        int count = erl.getSubscribeMap().size();
        assertEquals(13,count);
        for (Map.Entry<Event,CopyOnWriteArrayList<Subscription>> entry : erl.getSubscribeMap().entrySet()) {
            CopyOnWriteArrayList<Subscription> value = entry.getValue();
            assertEquals(2,value.size());
        }
        erl.unRegister(sbt);
        count = erl.getSubscribeMap().size();
        assertEquals(13,count);
        for (Map.Entry<Event,CopyOnWriteArrayList<Subscription>> entry : erl.getSubscribeMap().entrySet()) {
            CopyOnWriteArrayList<Subscription> value = entry.getValue();
            assertEquals(1,value.size());
        }
        erl.unRegister(sbtm);
        count = erl.getSubscribeMap().size();
        assertEquals(0,count);
    }

    // 测试的注册类和Event
    // ------------------------------ 测试的注册类 -----------------------------------------
    // ------------------------------ 测试的注册类 -----------------------------------------

    // ------------------------------ 事件 ------------------------------------------------
    // ------------------------------ 事件 ------------------------------------------------

}
