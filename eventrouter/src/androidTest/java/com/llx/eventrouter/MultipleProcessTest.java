package com.llx.eventrouter;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.llx.eventrouter.enty.SubscribeMultipleProcess;
import com.llx.eventrouter.execute.ThreadModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 测试RouterService的进程与其中一个通讯进程混在一起的时候的行为
 */
@RunWith(AndroidJUnit4.class)
public class MultipleProcessTest {

    @ClassRule
    public static final ServiceTestRule sServiceRule = new ServiceTestRule();
    private static final int DEFAULT_TIMEOUT = 100000;



    @BeforeClass
    public static void before() throws Exception {
    }

    @AfterClass
    public static void after() throws Exception {
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void receiveMethodFromRemoteService2Test() throws Exception {

        SubscribeMultipleProcess smp = new SubscribeMultipleProcess();
        Context context = InstrumentationRegistry.getTargetContext();
        EventRouter.init(context,context.getPackageName());

        EventRouter.get().register(smp);

        Intent intent = new Intent(context,RemoteService2.class);
        IBinder binder = sServiceRule.bindService(intent);
        IDebugBridge bridge = IDebugBridge.Stub.asInterface(binder);

        Thread.sleep(5000);

        bridge.setCmd(CMD.REMOTE_SEND);

        while (true) {
            Thread.sleep(10);
            if ("hello_world".equals(smp.msg)) {
                break;
            }
        }
    }
}
