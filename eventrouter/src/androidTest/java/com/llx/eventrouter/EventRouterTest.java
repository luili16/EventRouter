package com.llx.eventrouter;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.llx.eventrouter.enty.Event1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

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

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postDoubleTet() throws Exception {
        Log.d("main","double");
        double d = Double.MAX_VALUE;
        EventRouter.get().post(d,"Subscriber1_registerMethod21_double");

        while (true) {
            Thread.sleep(10);
            if (d == sDebugBridge.getVal().getD() && d == sDebugBridge.getVal().getDd()) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postBooleanTest() throws Exception {
        Log.d("main","boolean");
        boolean b = true;
        EventRouter.get().post(b,"Subscriber1_registerMethod25_boolean");

        while (true) {
            Thread.sleep(10);

            if (b == sDebugBridge.getVal().getBoo()) {
                break;
            }
        }
    }


    @Test(timeout = DEFAULT_TIMEOUT)
    public void postStringTest() throws Exception {
        Log.d("main","String");

        EventRouter.get().post("hello","Subscriber1_registerMethod21_String");

        while (true) {
            Thread.sleep(10);

            if ("hello".equals(sDebugBridge.getVal().getSs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postIntArrayTest() throws Exception {
        Log.d("main","int array");

        int[] is = new int[]{0,Integer.MIN_VALUE,Integer.MAX_VALUE};
        EventRouter.get().post(is,"Subscriber1_registerMethod9_int[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(is,sDebugBridge.getVal().getIs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postLongArrayTest() throws Exception {
        Log.d("main","long array");

        long[] ls = new long[]{0,Long.MAX_VALUE,Long.MIN_VALUE};
        EventRouter.get().post(ls,"Subscriber1_registerMethod10_long[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(ls,sDebugBridge.getVal().getLs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postCharArrayTest() throws Exception {
        Log.d("main","char array");

        char[] cs = new char[]{'x','y','z'};

        EventRouter.get().post(cs,"Subscriber1_registerMethod11_char[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(cs,sDebugBridge.getVal().getCs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postByteArrayTest() throws Exception {
        Log.d("main","byte array");

        byte[] bs = new byte[]{0,Byte.MAX_VALUE,Byte.MIN_VALUE};

        EventRouter.get().post(bs,"Subscriber1_registerMethod12_byte[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(bs,sDebugBridge.getVal().getBs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postFloatArrayTest() throws Exception {

        Log.d("main","float array");

        float[] fs = new float[]{0f,Float.MAX_VALUE,Float.MIN_VALUE};

        EventRouter.get().post(fs,"Subscriber1_registerMethod23_float[]");

        while (true) {
            Thread.sleep(10);
            if (Arrays.equals(fs,sDebugBridge.getVal().getFs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postDoubleArrayTest() throws Exception {
        Log.d("main","double array");

        double[] ds = new double[]{0,Double.MAX_VALUE,Double.MIN_VALUE};

        EventRouter.get().post(ds,"Subscriber1_registerMethod24_double[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(ds,sDebugBridge.getVal().getDs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postStringArrayTest() throws Exception {
        Log.d("main","string array");

        String[] ss = new String[]{"abc","def","ghiu"};

        EventRouter.get().post(ss,"Subscriber1_registerMethod24_String[]");

        while (true) {
            Thread.sleep(10);

            if (Arrays.equals(ss,sDebugBridge.getVal().getStrs())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postEventTest() throws Exception {
        Log.d("main","parcelable");

        Event1 event1 = new Event1("a","d");

        EventRouter.get().post(event1,"Subscriber1_registerMethod13_parcelable");

        while (true) {
            Thread.sleep(10);

            if (event1.equals(sDebugBridge.getVal().getEvent())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void postParcelableArrayTest() throws Exception {
        Log.d("main","parcelable array");

        ArrayList<Event1> event1s = new ArrayList<>();
        event1s.add(new Event1("ab","cd"));
        event1s.add(new Event1("cd,","ef"));
        event1s.add(new Event1("hj,","jk"));
        event1s.add(new Event1("lm,","no"));
        event1s.add(new Event1("pq,","rs"));

        EventRouter.get().post(event1s,"Subscriber1_registerMethod29_parcelableArrayList");

        while (true) {
            Thread.sleep(10);

            if (sDebugBridge.getVal().getEvent1s() != null && Arrays.equals(event1s.toArray(),sDebugBridge.getVal().getEvent1s().toArray())) {
                break;
            }
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnIntTest() throws Exception {
        int ret = (int) EventRouter.get().post(1234,"Subscriber1_registerMethod2_int",Integer.class);

        assertEquals(1234,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnLongTest() throws Exception {
        long ret = (long) EventRouter.get().post(1234L,"Subscriber1_registerMethod3_long",Long.class);
        assertEquals(1234L,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnCharTest() throws Exception {
        char ret = (char) EventRouter.get().post('a',"Subscriber1_registerMethod4_char",Character.class);
        assertEquals('a',ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnByteTest() throws Exception {
        Byte b = (Byte) EventRouter.get().post(new Integer(23).byteValue(),"Subscriber1_registerMethod5_byte",Byte.class);
        assertEquals(new Integer(23).byteValue(),b.byteValue());
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnFloatTest() throws Exception {
        float f = (float) EventRouter.get().post(0.123f,"Subscriber1_registerMethod19_float",Float.class);
        assertEquals(0.123f,f,0.01f);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnDoubleTest() throws Exception {
        double d = (double) EventRouter.get().post(0.123d,"Subscriber1_registerMethod21_double",Double.class);
        assertEquals(0.123d,d,0.1f);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnBooleanTest() throws Exception {
        boolean b = (boolean) EventRouter.get().post(true,"Subscriber1_registerMethod27_boolean",Boolean.class);
        assertEquals(true,b);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnStringTest() throws Exception {
        String s = (String) EventRouter.get().post("hello","Subscriber1_registerMethod26_String",String.class);
        assertEquals("hello",s);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnParcelableTest() throws Exception {
        Event1 event1 = new Event1("dkj","kdjk");
        Event1 ret = (Event1) EventRouter.get().post(event1,"Subscriber1_registerMethod13_parcelable",Event1.class);
        assertEquals(event1,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnIntArrayTest() throws Exception {
        int[] is = new int[]{1,2,Integer.MAX_VALUE};
        int[] ret = (int[]) EventRouter.get().post(is,"Subscriber1_registerMethod9_int[]",int[].class);
        assertArrayEquals(is,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnLongArrayTest() throws Exception {
        long[] ls = new long[]{1,2,Long.MAX_VALUE};
        long[] ret = (long[]) EventRouter.get().post(ls,"Subscriber1_registerMethod10_long[]",long[].class);
        assertArrayEquals(ls,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnCharArrayTest() throws Exception {
        char[] cs = new char[]{'a','c','d'};
        char[] ret = (char[]) EventRouter.get().post(cs,"Subscriber1_registerMethod11_char[]",char[].class);
        assertArrayEquals(cs,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnByteArrayTest() throws Exception {
        byte[] bytes = new byte[]{1,23,4};
        byte[] ret = (byte[]) EventRouter.get().post(bytes,"Subscriber1_registerMethod12_byte[]",byte[].class);
        assertArrayEquals(bytes,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnFloatArrayTest() throws Exception {
        float[] fs = new float[]{0.01f,0.02f};
        float[] ret = (float[]) EventRouter.get().post(fs,"Subscriber1_registerMethod23_float[]",float[].class);
        assertArrayEquals(fs,ret,0.2f);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnDoubleArrayTest() throws Exception {
        double[] ds = new double[]{0.02d,0.03d};
        double[] ret = (double[]) EventRouter.get().post(ds,"Subscriber1_registerMethod24_double[]",double[].class);
        assertArrayEquals(ds,ret,0.01f);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnBooleanArrayTest() throws Exception {
        boolean[] bs = new boolean[]{false,true,true};
        boolean[] ret = (boolean[]) EventRouter.get().post(bs,"Subscriber1_registerMethod28_boolean[]",boolean[].class);
        assertArrayEquals(bs,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnStringArrayTest() throws Exception {
        String[] str = new String[]{"dk","dkfjd","kkk"};
        String[] ret = (String[]) EventRouter.get().post(str,"Subscriber1_registerMethod25_String[]",String[].class);
        assertArrayEquals(str,ret);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void returnParcelableArrayTest() throws Exception {
        ArrayList<Event1> event1s= new ArrayList<>();
        event1s.add(new Event1("a","b"));
        event1s.add(new Event1("c","d"));
        event1s.add(new Event1("f","g"));
        ArrayList<Event1> ret = (ArrayList<Event1>) EventRouter.get().post(event1s,"Subscriber1_registerMethod13_parcelableArrayList",ArrayList.class);
        assertArrayEquals(event1s.toArray(),ret.toArray());
    }
}
