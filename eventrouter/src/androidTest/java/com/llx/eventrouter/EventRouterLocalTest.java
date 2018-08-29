package com.llx.eventrouter;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.llx.eventrouter.enty.Event1;
import com.llx.eventrouter.enty.SubscriberParamType2;
import com.llx.eventrouter.enty.SubscriberExp;
import com.llx.eventrouter.enty.SubscriberParamType;
import com.llx.eventrouter.enty.SubscriberRegister;
import com.llx.eventrouter.enty.SubscriberRegister2;
import com.llx.eventrouter.enty.SubscriberReturnType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void registerTest() throws Exception {
        SubscriberRegister sr = new SubscriberRegister();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sr);
        int count = erl.getSubscribeMap().size();
        assertEquals(4, count);
        erl.unRegister(sr);
        count = erl.getSubscribeMap().size();
        assertEquals(0, count);
    }

    @Test
    public void registerThrowExpTest() {
    }

    /**
     * 测试多个类中注册到了同一个事件上
     */
    @Test
    public void registerDupleTest() throws Exception {
        SubscriberRegister sbt = new SubscriberRegister();
        SubscriberRegister2 sbtm = new SubscriberRegister2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);
        int count = erl.getSubscribeMap().size();
        assertEquals(4, count);
        for (Map.Entry<Event, CopyOnWriteArrayList<Subscription>> entry : erl.getSubscribeMap().entrySet()) {
            CopyOnWriteArrayList<Subscription> value = entry.getValue();
            assertEquals(2, value.size());
        }
        erl.unRegister(sbt);
        count = erl.getSubscribeMap().size();
        assertEquals(4, count);
        for (Map.Entry<Event, CopyOnWriteArrayList<Subscription>> entry : erl.getSubscribeMap().entrySet()) {
            CopyOnWriteArrayList<Subscription> value = entry.getValue();
            assertEquals(1, value.size());
        }
        erl.unRegister(sbtm);
        count = erl.getSubscribeMap().size();
        assertEquals(0, count);
    }

    @Test
    public void postVoidTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);
        // void
        erl.post(null, "Subscriber1_registerMethod1_void");
        assertEquals("call_void",sbt.tag);
        assertEquals("call_void",sbtm.tag);
    }


    @Test
    public void postIntTest() throws Exception {

        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);


        // int
        erl.post(Integer.MAX_VALUE, "Subscriber1_registerMethod2_int");
        assertEquals(Integer.MAX_VALUE, sbt.i);
        assertEquals(new Integer(Integer.MAX_VALUE), sbt.ii);
        assertEquals(Integer.MAX_VALUE, sbtm.i);
        assertEquals(new Integer(Integer.MAX_VALUE), sbtm.ii);

    }

    @Test
    public void postLongTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        // long
        erl.post(Long.MAX_VALUE, "Subscriber1_registerMethod3_long");
        assertEquals(Long.MAX_VALUE, sbt.l);
        assertEquals(new Long(Long.MAX_VALUE), sbt.ll);
        assertEquals(Long.MAX_VALUE, sbtm.l);
        assertEquals(new Long(Long.MAX_VALUE), sbtm.ll);
    }

    @Test
    public void postCharTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post('a', "Subscriber1_registerMethod4_char");
        assertEquals('a', sbt.c);
        assertEquals(new Character('a'), sbt.cc);
        assertEquals('a', sbtm.c);
        assertEquals(new Character('a'), sbtm.cc);
    }

    @Test
    public void postByteTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post(Byte.MAX_VALUE, "Subscriber1_registerMethod5_byte");
        assertEquals(Byte.MAX_VALUE, sbt.b);
        assertEquals(new Byte(Byte.MAX_VALUE), sbt.bb);
        assertEquals(Byte.MAX_VALUE, sbtm.b);
        assertEquals(new Byte(Byte.MAX_VALUE), sbtm.bb);
    }

    @Test
    public void postFloatTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post(Float.MAX_VALUE, "Subscriber1_registerMethod19_float");
        assertEquals(Float.MAX_VALUE, sbt.f, 0.01f);
        assertEquals(Float.MAX_VALUE, sbt.ff, 0.01f);
        assertEquals(Float.MAX_VALUE, sbtm.f, 0.01f);
        assertEquals(Float.MAX_VALUE, sbtm.ff, 0.01f);
    }

    @Test
    public void postDoubleTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post(Double.MAX_VALUE, "Subscriber1_registerMethod21_double");
        assertEquals(Double.MAX_VALUE, sbt.d, 0.01f);
        assertEquals(Double.MAX_VALUE, sbt.dd, 0.01f);
        assertEquals(Double.MAX_VALUE, sbtm.d, 0.01f);
        assertEquals(Double.MAX_VALUE, sbtm.dd, 0.01f);
    }

    @Test
    public void postBooleanTest() throws Exception {

        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post(true,"Subscriber1_registerMethod25_boolean");

        assertEquals(true,sbt.bo);
        assertEquals(true,sbt.boo);
    }

    @Test
    public void postStringTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        erl.post("hello world", "Subscriber1_registerMethod21_String");
        assertEquals("hello world", sbt.ss);
    }

    @Test
    public void postParcelableTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        Event1 event1 = new Event1("aa", "bb");
        erl.post(event1, "Subscriber1_registerMethod13_parcelable");
        assertEquals(event1, sbt.event);
        assertEquals(event1, sbtm.event);
    }

    @Test
    public void postIntArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        int[] a = new int[]{1, 2, 3};
        erl.post(a, "Subscriber1_registerMethod9_int[]");
        assertArrayEquals(a, sbt.is);
        assertArrayEquals(a, sbtm.is);
    }

    @Test
    public void postLongArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        long[] a = new long[]{Long.MAX_VALUE, Long.MIN_VALUE, 0};
        erl.post(a, "Subscriber1_registerMethod10_long[]");
        assertArrayEquals(a, sbt.ls);
        assertArrayEquals(a, sbtm.ls);
    }

    @Test
    public void postCharArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        char[] a = new char[]{'a', 'b', 'd'};
        erl.post(a, "Subscriber1_registerMethod11_char[]");
        assertArrayEquals(a, sbt.cs);
        assertArrayEquals(a, sbtm.cs);

    }

    @Test
    public void postByteArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        byte[] a = new byte[]{Byte.MIN_VALUE, Byte.MIN_VALUE, 0};
        erl.post(a, "Subscriber1_registerMethod12_byte[]");
        assertArrayEquals(a, sbt.bs);
        assertArrayEquals(a, sbtm.bs);
    }

    @Test
    public void postFloatArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        float[] f = new float[]{0.0f, Float.MAX_VALUE, Float.MIN_VALUE};
        erl.post(f, "Subscriber1_registerMethod23_float[]");
        assertArrayEquals(f, sbt.fs, 0.01f);
        assertArrayEquals(f, sbtm.fs, 0.01f);
    }

    @Test
    public void postDoubleArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        double[] d = new double[]{0.0f, Double.MAX_VALUE, Double.MIN_VALUE};
        erl.post(d, "Subscriber1_registerMethod24_double[]");
        assertArrayEquals(d, sbt.ds, 0.01f);
        assertArrayEquals(d, sbtm.ds, 0.01f);
    }

    @Test
    public void postBooleanArrayTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        boolean[] bs = new boolean[]{true,false,true};
        erl.post(bs,"Subscriber1_registerMethod30_boolean[]");
        assertArrayEquals(bs,sbt.boos);
    }

    @Test
    public void postStringArrayTest() throws Exception {

        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        String[] strs = new String[]{"aa", "bb", "cc"};
        erl.post(strs, "Subscriber1_registerMethod24_String[]");
        assertArrayEquals(strs, sbt.strs);
    }

    @Test
    public void postParcelableArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Event1> event1s = new ArrayList<>();
        event1s.add(new Event1("ab","cd"));
        event1s.add(new Event1("cd,","ef"));
        event1s.add(new Event1("hj,","jk"));
        event1s.add(new Event1("lm,","no"));
        event1s.add(new Event1("pq,","rs"));

        erl.post(event1s,"Subscriber1_registerMethod29_parcelableArrayList");
        assertArrayEquals(event1s.toArray(),sbt.event1s.toArray());

    }

    @Test
    public void postIntegerArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(0);
        integers.add(Integer.MIN_VALUE);
        integers.add(Integer.MAX_VALUE);

        erl.post(integers,"Subscriber1_registerMethod29_integerArrayList");
        assertArrayEquals(integers.toArray(),sbt.integers.toArray());
    }

    @Test
    public void postLongArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        longs.add(0L);
        longs.add(Long.MIN_VALUE);
        longs.add(Long.MAX_VALUE);

        erl.post(longs,"Subscriber1_registerMethod29_longArrayList");
        assertArrayEquals(longs.toArray(),sbt.longs.toArray());
    }

    @Test
    public void postCharacterArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Character> characters = new ArrayList<>();
        characters.add('a');
        characters.add('&');
        characters.add('z');

        erl.post(characters,"Subscriber1_registerMethod29_charactersArrayList");

        assertArrayEquals(characters.toArray(),sbt.characters.toArray());

    }

    @Test
    public void postByteArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 1);
        bytes.add((byte) 0);
        bytes.add(Byte.MIN_VALUE);
        bytes.add(Byte.MAX_VALUE);

        erl.post(bytes,"Subscriber1_registerMethod29_bytesArrayList");
        assertArrayEquals(bytes.toArray(),sbt.bytes.toArray());
    }

    @Test
    public void postFloatArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Float> floats = new ArrayList<>();
        floats.add(0.1f);
        floats.add(0.22f);
        floats.add(0.001f);
        floats.add(Float.MIN_VALUE);
        floats.add(Float.MAX_VALUE);

        erl.post(floats,"Subscriber1_registerMethod29_floatsArrayList");

        assertArrayEquals(floats.toArray(),sbt.floats.toArray());
    }

    @Test
    public void postDoubleArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(0.1d);
        doubles.add(0.2d);
        doubles.add(0.4d);
        doubles.add(Double.MIN_VALUE);
        doubles.add(Double.MAX_VALUE);

        erl.post(doubles,"Subscriber1_registerMethod29_doubleArrayList");

        assertArrayEquals(doubles.toArray(),sbt.doubles.toArray());
    }

    @Test
    public void postBooleanArrayListTest() throws Exception {
        SubscriberParamType sbt = new SubscriberParamType();
        SubscriberParamType2 sbtm = new SubscriberParamType2();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(sbt);
        erl.register(sbtm);

        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);

        erl.post(booleans,"Subscriber1_registerMethod29_booleanArrayList");

        assertArrayEquals(booleans.toArray(),sbt.booleans.toArray());

    }

    @Test
    public void returnIntTest() throws Exception {

        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        int ret = (int) erl.post(1234, "Subscriber1_registerMethod2_int", Integer.class.getCanonicalName());
        Integer ret1 = (Integer) erl.post(1234, "Subscriber1_registerMethod2_int", Integer.class.getCanonicalName());
        assertEquals(1234, ret);
        assertEquals(new Integer(1234), ret1);
    }

    @Test
    public void returnLongTest() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        long ret = (long) erl.post(Long.MAX_VALUE, "Subscriber1_registerMethod3_long", Long.class.getCanonicalName());
        Long ret1 = (Long) erl.post(Long.MAX_VALUE, "Subscriber1_registerMethod3_long", Long.class.getCanonicalName());
        ;

        assertEquals(Long.MAX_VALUE, ret);
        assertEquals(new Long(Long.MAX_VALUE), ret1);
    }

    @Test
    public void returnCharTest() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        char ret = (char) erl.post('d', "Subscriber1_registerMethod4_char", Character.class.getCanonicalName());
        Character ret1 = (Character) erl.post('d', "Subscriber1_registerMethod4_char", Character.class.getCanonicalName());
        assertEquals('d', ret);
        assertEquals(new Character('d'), ret1);
    }

    @Test
    public void returnByteValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        byte b = (byte) erl.post(Byte.MIN_VALUE, "Subscriber1_registerMethod5_byte", Byte.class.getCanonicalName());
        Byte bc = (Byte) erl.post(Byte.MIN_VALUE, "Subscriber1_registerMethod5_byte", Byte.class.getCanonicalName());
        assertEquals(Byte.MIN_VALUE, b);
        assertEquals(new Byte(Byte.MIN_VALUE), bc);
    }

    @Test
    public void returnFloatValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        float f = (float) erl.post(Float.MIN_VALUE, "Subscriber1_registerMethod19_float", Float.class.getCanonicalName());
        Float ff = (Float) erl.post(Float.MIN_VALUE, "Subscriber1_registerMethod19_float", Float.class.getCanonicalName());
        assertEquals(Float.MIN_VALUE, f, 0.1f);
        assertEquals(Float.MIN_VALUE, ff, 0.1f);
    }

    @Test
    public void returnDoubleValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        double d = (double) erl.post(Double.MAX_VALUE, "Subscriber1_registerMethod21_double", Double.class.getCanonicalName());
        Double dd = (Double) erl.post(Double.MAX_VALUE, "Subscriber1_registerMethod21_double", Double.class.getCanonicalName());
        assertEquals(Double.MAX_VALUE, d, 0.1f);
        assertEquals(Double.MAX_VALUE, dd, 0.1f);
    }

    @Test
    public void returnBooleanValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        boolean b =true;
        boolean bt = (boolean) erl.post(true,"Subscriber1_registerMethod27_boolean",Boolean.class.getCanonicalName());
        assertEquals(b,bt);
    }

    @Test
    public void returnStringValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        String str = (String) erl.post("hello world", "Subscriber1_registerMethod26_String", String.class.getCanonicalName());
        assertEquals("hello world", str);
    }


    @Test
    public void returnIntArrayValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);
        int[] p = new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] as = (int[]) erl.post(p, "Subscriber1_registerMethod9_int[]", int[].class.getCanonicalName());
        assertArrayEquals(p, as);
    }

    @Test
    public void returnLongArrayValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        long[] p = new long[]{0, Long.MIN_VALUE, Long.MAX_VALUE};
        long[] ps = (long[]) erl.post(p, "Subscriber1_registerMethod10_long[]", long[].class.getCanonicalName());
        assertArrayEquals(p, ps);
    }

    @Test
    public void returnCharArrayValue() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        char[] p = new char[]{'a', 'b', 'c'};
        char[] ps = (char[]) erl.post(p, "Subscriber1_registerMethod11_char[]", char[].class.getCanonicalName());
        assertArrayEquals(p, ps);
    }

    @Test
    public void returnByteArray() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        byte[] p = new byte[]{0, Byte.MIN_VALUE, Byte.MAX_VALUE};
        byte[] ps = (byte[]) erl.post(p, "Subscriber1_registerMethod12_byte[]", byte[].class.getCanonicalName());
        assertArrayEquals(p, ps);
    }

    @Test
    public void returnFloatArray() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        float[] p = new float[]{0.0f, Float.MIN_VALUE, Float.MAX_VALUE};
        float[] ps = (float[]) erl.post(p, "Subscriber1_registerMethod23_float[]", float[].class.getCanonicalName());

        assertArrayEquals(p, ps, 0.1f);
    }

    @Test
    public void returnDoubleArray() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        double[] p = new double[]{0.0d, Double.MIN_VALUE, Double.MAX_VALUE};
        double[] ds = (double[]) erl.post(p, "Subscriber1_registerMethod24_double[]", double[].class.getCanonicalName());
        assertArrayEquals(p, ds, 0.1f);
    }

    @Test
    public void returnBooleanArray() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        boolean[] b = new boolean[]{false,false,true};
        boolean[] bs = (boolean[]) erl.post(b,"Subscriber1_registerMethod28_boolean[]",boolean[].class.getCanonicalName());
        assertArrayEquals(b,bs);
    }

    @Test
    public void returnStringArray() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        String[] strs = new String[]{"aa","cc","dd"};
        String[] rets = (String[]) erl.post(strs,"Subscriber1_registerMethod25_String[]",String[].class.getCanonicalName());
        assertArrayEquals(strs,rets);
    }

    @Test
    public void returnParcelableArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Event1> event1s = new ArrayList<>();
        event1s.add(new Event1("a","b"));
        event1s.add(new Event1("c","d"));
        event1s.add(new Event1("e","f"));
        event1s.add(new Event1("h","g"));

        ArrayList<Event1> ret = (ArrayList<Event1>) erl.post(event1s,"Subscriber1_registerMethod13_parcelableArrayList",ArrayList.class.getCanonicalName());

        assertArrayEquals(event1s.toArray(),ret.toArray());
    }

    @Test
    public void returnIntegerArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(Integer.MIN_VALUE);
        integers.add(Integer.MAX_VALUE);

        ArrayList<Integer> ret = (ArrayList<Integer>) erl.post(integers,"Subscriber1_registerMethod13_integerArrayList",ArrayList.class.getCanonicalName());
        assertArrayEquals(integers.toArray(),ret.toArray());
    }

    @Test
    public void returnLongArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(0L);
        longs.add(Long.MAX_VALUE);
        longs.add(Long.MIN_VALUE);

        ArrayList<Long> ret = (ArrayList<Long>) erl.post(longs,"Subscriber1_registerMethod13_longArrayList",ArrayList.class.getCanonicalName());
        assertArrayEquals(longs.toArray(),ret.toArray());
    }

    @Test
    public void returnCharacterArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Character> characters = new ArrayList<>();
        characters.add('a');
        characters.add('b');
        characters.add('c');

        ArrayList<Character> cs = (ArrayList<Character>) erl.post(characters,"Subscriber1_registerMethod13_characterArrayList",ArrayList.class.getCanonicalName());
        assertArrayEquals(characters.toArray(),cs.toArray());
    }

    @Test
    public void returnByteArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 2);
        bytes.add((byte) 3);
        ArrayList<Byte> rets = (ArrayList<Byte>) erl.post(bytes,"Subscriber1_registerMethod13_bytesArrayList",ArrayList.class.getCanonicalName());
        assertArrayEquals(bytes.toArray(),rets.toArray());
    }

    @Test
    public void returnFloatArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Float> floats = new ArrayList<>();
        floats.add(0.10f);
        floats.add(-0.1f);
        floats.add(Float.MIN_VALUE);
        floats.add(Float.MAX_VALUE);

        ArrayList<Float> ret = (ArrayList<Float>) erl.post(floats,"Subscriber1_registerMethod13_floatArrayList",ArrayList.class.getCanonicalName());
        assertArrayEquals(floats.toArray(),ret.toArray());
    }

    @Test
    public void returnDoubleArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(0.1d);
        doubles.add(0.2d);
        doubles.add(0.3d);

        ArrayList<Double> ret = (ArrayList<Double>) erl.post(doubles,"Subscriber1_registerMethod13_doubleArrayList",ArrayList.class.getCanonicalName());

        assertArrayEquals(doubles.toArray(),ret.toArray());
    }

    public void returnBooleanArrayList() throws Exception {
        SubscriberReturnType srt = new SubscriberReturnType();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(srt);

        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add(false);
        booleans.add(true);
        booleans.add(false);

        ArrayList<Boolean> ret = (ArrayList<Boolean>) erl.post(booleans,"Subscriber1_registerMethod13_booleanArrayList",ArrayList.class.getCanonicalName());

        assertArrayEquals(booleans.toArray(),ret.toArray());
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionIntegerArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionLongArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionFloatArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionDoubleArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionCharacterArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionByteArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionBooleanArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionParcelableArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test(expected = UnSupportParameterException.class)
    public void exceptionObjectArrayTest() throws Exception {
        SubscriberExp se = new SubscriberExp();
        EventRouterLocal erl = new EventRouterLocal();
        erl.register(se);
    }

    @Test
    public void registerConcurrentTest() {
    }
}
