package com.llx.eventrouter;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EventRouterTest {

    @ClassRule
    public static final ServiceTestRule sServiceRule = new ServiceTestRule();

    private static final int DEFAULT_TIMEOUT = 3000;

    private static IDebugBridge sDebugBridge;
    @BeforeClass
    public static void before() throws Exception{

        Context context = InstrumentationRegistry.getTargetContext();
        Intent servive = new Intent(context,RemoteService.class);
        IBinder binder = sServiceRule.bindService(servive);
        sDebugBridge = IDebugBridge.Stub.asInterface(binder);
        Log.d("main","package name is " + context.getPackageName());
        EventRouter.init(context,context.getPackageName());

        Thread.sleep(5000);
    }

    @AfterClass
    public static void after() {
        sServiceRule.unbindService();
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postVoidTest() throws Exception {
        Log.d("main","void");
        EventRouter.get().post(null,"Subscriber1_registerMethod1_void",Void.class);

        while (true) {
            Thread.sleep(10);
            if (TextUtils.equals("call_void",sDebugBridge.getVal().getTag())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postIntTest() throws Exception {
        Log.d("main","int");
        EventRouter.get().post(Integer.MAX_VALUE,"Subscriber1_registerMethod2_int",Void.class);

        while (true) {
            Thread.sleep(10);
            if (Integer.MAX_VALUE == sDebugBridge.getVal().getI() && Integer.MAX_VALUE == sDebugBridge.getVal().getIi()) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postLongTest() throws Exception {
        Log.d("main","long");
        EventRouter.get().post(Long.MAX_VALUE,"Subscriber1_registerMethod3_long",Void.class);

        while (true) {
            Thread.sleep(10);
            if (Long.MAX_VALUE == sDebugBridge.getVal().getL() && Long.MAX_VALUE == sDebugBridge.getVal().getLl()) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postCharTest() throws Exception {
        Log.d("main","char");
        EventRouter.get().post('a',"Subscriber1_registerMethod4_char");

        while (true) {
            Thread.sleep(10);
            if ('a' == sDebugBridge.getVal().getC() && 'a' == sDebugBridge.getVal().getCc()) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postByteTest() throws Exception {
        Log.d("main","byte");
        EventRouter.get().post((byte)12,"Subscriber1_registerMethod5_byte");

        while (true) {
            Thread.sleep(10);
            if (12 == sDebugBridge.getVal().getB() && 12 == sDebugBridge.getVal().getBb()) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postFloatTest() throws Exception {

        Log.d("main","float");
        float f = 0.12f;

        EventRouter.get().post(f,"Subscriber1_registerMethod19_float");

        while (true) {
            Thread.sleep(10);
            if (f == sDebugBridge.getVal().getF() && f == sDebugBridge.getVal().getFf()) {
                break;
            }
        }
    }


    @Test
    public void postDoubleTest() throws Exception {

    }

    @Test
    public void test() {



    }

}
