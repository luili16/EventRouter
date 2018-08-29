package com.llx.eventrouter;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(AndroidJUnit4.class)
public class ConcurrentTest {

    @ClassRule
    public static final ServiceTestRule sServiceRule = new ServiceTestRule();

    private static final int DEFAULT_TIMEOUT = 3000;

    private static IDebugBridge sDebugBridge;

    @BeforeClass
    public static void before() throws Exception {

        Context context = InstrumentationRegistry.getTargetContext();
        Intent servive = new Intent(context, RemoteService.class);
        IBinder binder = sServiceRule.bindService(servive);
        sDebugBridge = IDebugBridge.Stub.asInterface(binder);
        Log.d("main", "package name is " + context.getPackageName());
        EventRouter.init(context, context.getPackageName());

        Thread.sleep(5000);


    }

    @AfterClass
    public static void after() {
        sServiceRule.unbindService();


    }

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Test
    public void currentTest() {
    }
}
