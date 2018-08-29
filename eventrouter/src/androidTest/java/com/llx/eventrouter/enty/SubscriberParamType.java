package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Event;
import com.llx.eventrouter.Subscriber;
import com.llx.eventrouter.execute.ThreadModel;

import java.util.ArrayList;
import java.util.Arrays;

public class SubscriberParamType {

    private static final String TAG = "SubscriberParamType";

    public String tag = "";
    public int i;
    public long l;
    public char c;
    public byte b;
    public float f;
    public double d;
    public boolean bo;
    public Integer ii;
    public Long ll;
    public Byte bb;
    public Character cc;
    public Float ff;
    public Double dd;
    public String ss;
    public Boolean boo;
    public int[] is;
    public long[] ls;
    public char[] cs;
    public byte[] bs;
    public float[] fs;
    public double[] ds;
    public boolean[] boos;
    public String[] strs;
    public Event1 event;
    public ArrayList<Event1> event1s;
    public ArrayList<Integer> integers;
    public ArrayList<Long> longs;
    public ArrayList<Character> characters;
    public ArrayList<Byte> bytes;
    public ArrayList<Float> floats;
    public ArrayList<Double> doubles;
    public ArrayList<Boolean> booleans;

    public ValueWrapper mValueWrapper;

    public SubscriberParamType(ValueWrapper valueWrapper) {
        mValueWrapper = valueWrapper;
    }

    public SubscriberParamType() {
        mValueWrapper = new ValueWrapper();
    }

    @Subscriber(tag = "Subscriber1_registerMethod1_void",threadModel = ThreadModel.POST)
    public void registerMethod1() {
        Log.d(TAG, "Subscriber1_registerMethod1_void");
        this.tag = "call_void";
        mValueWrapper.setTag("call_void");
    }

    // ----- int Integer 相同 ------
    @Subscriber(tag = "Subscriber1_registerMethod2_int", threadModel = ThreadModel.POST)
    public void registerMethod2(int i) {
        Log.d(TAG, "Subscriber1_registerMethod2_int = " + i);
        this.i = i;
        mValueWrapper.setI(i);
    }

    @Subscriber(tag = "Subscriber1_registerMethod2_int", threadModel = ThreadModel.POST)
    public void registerMethod6(Integer i) {
        Log.d(TAG, "Subscriber1_registerMethod_Integer = " + i);
        this.ii = i;
        mValueWrapper.setIi(ii);
    }
    // ----- int Integer 相同 ------

    // ----- long Long 相同 -------
    @Subscriber(tag = "Subscriber1_registerMethod3_long", threadModel = ThreadModel.POST)
    public void registerMethod3(long l) {
        Log.d(TAG, "Subscriber1_registerMethod3_long = " + l);
        this.l = l;
        mValueWrapper.setL(l);
    }

    @Subscriber(tag = "Subscriber1_registerMethod3_long", threadModel = ThreadModel.POST)
    public void registerMethod7(Long l) {
        Log.d(TAG, "Subscriber1_registerMethod7_Long = " + l);
        this.ll = l;
        mValueWrapper.setLl(l);
    }
    // ----- long Long 相同 ----------

    // ----- char character 相同 ----------
    @Subscriber(tag = "Subscriber1_registerMethod4_char", threadModel = ThreadModel.POST)
    public void registerMethod4(char c) {
        Log.d(TAG, "Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.c = c;
        mValueWrapper.setC(c);
    }

    @Subscriber(tag = "Subscriber1_registerMethod4_char", threadModel = ThreadModel.POST)
    public void registerMethod14(Character c) {
        Log.d(TAG, "Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.cc = c;
        mValueWrapper.setCc(c);
    }
    // ---- char character is same

    // ----- byte Byte is same ------
    @Subscriber(tag = "Subscriber1_registerMethod5_byte", threadModel = ThreadModel.POST)
    public void registerMethod5(byte b) {
        Log.d(TAG, "Subscriber1_registerMethod5_byte = " + b);
        this.b = b;
        mValueWrapper.setB(b);
    }

    @Subscriber(tag = "Subscriber1_registerMethod5_byte", threadModel = ThreadModel.POST)
    public void registerMethod8(Byte b) {
        Log.d(TAG, "Subscriber1_registerMethod8_Byte = " + b);
        this.bb = b;
        mValueWrapper.setBb(b);
    }
    // ---- byte Byte is same -----------------

    @Subscriber(tag = "Subscriber1_registerMethod19_float", threadModel = ThreadModel.POST)
    public void registerMethod19(float f) {
        Log.d(TAG, "Subscriber1_registerMethod19_float = " + f);
        this.f = f;
        mValueWrapper.setF(f);
    }

    @Subscriber(tag = "Subscriber1_registerMethod19_float", threadModel = ThreadModel.POST)
    public void registerMethod20(Float f) {
        Log.d(TAG, "Subscriber1_registerMethod19_float = " + f);
        this.ff = f;
        mValueWrapper.setFf(f);
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_double", threadModel = ThreadModel.POST)
    public void registerMethod21(double f) {
        Log.d(TAG, "Subscriber1_registerMethod21_double = " + f);
        this.d = f;
        mValueWrapper.setD(f);
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_double", threadModel = ThreadModel.POST)
    public void registerMethod22(Double f) {
        Log.d(TAG, "Subscriber1_registerMethod21_Double = " + f);
        this.dd = f;
        mValueWrapper.setDd(f);
    }

    @Subscriber(tag = "Subscriber1_registerMethod25_boolean", threadModel = ThreadModel.POST)
    public void registerMethod25(boolean b) {
        Log.d(TAG,"Subscriber1_registerMethod25_boolean = " + b);
        this.bo = b;
        mValueWrapper.setBo(b);
    }

    @Subscriber(tag = "Subscriber1_registerMethod25_boolean", threadModel = ThreadModel.POST)
    public void registerMethod26(Boolean b) {
        Log.d(TAG,"Subscriber1_registerMethod25_boolean = " + b);
        this.boo = b;
        mValueWrapper.setBoo(b);
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_String", threadModel = ThreadModel.POST)
    public void registerMethod23(String s) {
        Log.d(TAG,"Subscriber1_registerMethod21_String");
        this.ss = s;
        mValueWrapper.setSs(s);
    }

    // ---- Integer[] can not support
    @Subscriber(tag = "Subscriber1_registerMethod9_int[]", threadModel = ThreadModel.POST)
    public void registerMethod9(int[] is) {
        Log.d(TAG, "Subscriber1_registerMethod9_int[] = " + Arrays.asList(is));
        this.is = is;
        mValueWrapper.setIs(is);
    }


    // ---- Long[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod10_long[]", threadModel = ThreadModel.POST)
    public void registerMethod10(long[] ls) {
        Log.d(TAG, "Subscriber1_registerMethod10_long[] = " + Arrays.asList(ls));
        this.ls = ls;
        mValueWrapper.setLs(ls);
    }

    // ---- Character[] can't support-------
    @Subscriber(tag = "Subscriber1_registerMethod11_char[]", threadModel = ThreadModel.POST)
    public void registerMethod11(char[] cs) {
        Log.d(TAG, "Subscriber1_registerMethod11_char[] = " + Arrays.asList(cs));
        this.cs = cs;
        mValueWrapper.setCs(cs);
    }

    // ---- Byte[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod12_byte[]", threadModel = ThreadModel.POST)
    public void registerMethod12(byte[] bs) {
        Log.d(TAG, "Subscriber1_registerMethod12_byte[] = " + Arrays.asList(bs));
        this.bs = bs;
        mValueWrapper.setBs(bs);
    }

    @Subscriber(tag = "Subscriber1_registerMethod23_float[]", threadModel = ThreadModel.POST)
    public void registerMethod23(float[] fs) {
        Log.d(TAG, "Subscriber1_registerMethod23_float");
        this.fs = fs;
        mValueWrapper.setFs(fs);
    }

    @Subscriber(tag = "Subscriber1_registerMethod24_double[]", threadModel = ThreadModel.POST)
    public void registerMethod24(double[] ds) {
        Log.d(TAG, "Subscriber1_registerMethod24_double[]");
        this.ds = ds;
        mValueWrapper.setDs(ds);
    }

    @Subscriber(tag = "Subscriber1_registerMethod30_boolean[]",threadModel = ThreadModel.POST)
    public void registerMethod30(boolean[] bs) {
        Log.d(TAG,"Subscriber1_registerMethod30_boolean[]");
        this.boos = bs;
        mValueWrapper.setBos(bs);
    }

    @Subscriber(tag = "Subscriber1_registerMethod24_String[]",threadModel = ThreadModel.POST)
    public void registerMethod25(String[] ss) {
        Log.d(TAG,"Subscriber1_registerMethod24_String[] = " + Arrays.toString(ss));
        this.strs = ss;
        mValueWrapper.setStrs(ss);
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelable", threadModel = ThreadModel.POST)
    public void registerMethod13(Event1 event) {
        Log.e(TAG, "Subscriber1_registerMethod13_parcelable = " + event.toString());
        this.event = event;
        mValueWrapper.setEvent(event);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_parcelableArrayList",threadModel = ThreadModel.POST)
    public void registerMethod29(ArrayList<Event1> event1s) {
        Log.d(TAG,"Subscriber1_registerMethod29_parcelableArrayList = " + event1s.toString());
        this.event1s = event1s;
        mValueWrapper.setEvent1s(event1s);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_integerArrayList",threadModel = ThreadModel.POST)
    public void registerMethod31(ArrayList<Integer> integers) {
        Log.d(TAG,"Subscriber1_registerMethod29_integerArrayList = " + integers.toString() );
        this.integers = integers;
        mValueWrapper.setIntegers(integers);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_longArrayList",threadModel = ThreadModel.POST)
    public void registerMethod32(ArrayList<Long> longs) {
        Log.d(TAG,"Subscriber1_registerMethod29_longArrayList = " + longs.toString());
        this.longs = longs;
        mValueWrapper.setLongs(longs);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_charactersArrayList",threadModel = ThreadModel.POST)
    public void registerMethod33(ArrayList<Character> characters) {
        Log.d(TAG,"Subscriber1_registerMethod29_charactersArrayList = " + characters.toString());
        this.characters = characters;
        mValueWrapper.setCharacters(characters);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_bytesArrayList",threadModel = ThreadModel.POST)
    public void registerMethod34(ArrayList<Byte> bytes) {
        Log.d(TAG,"Subscriber1_registerMethod29_bytesArrayList = " + bytes.toString());
        this.bytes = bytes;
        mValueWrapper.setBytes(bytes);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_floatsArrayList",threadModel = ThreadModel.POST)
    public void registerMethod35(ArrayList<Float> floats) {
        Log.d(TAG,"Subscriber1_registerMethod29_floatArrayList = " + floats.toString());
        this.floats = floats;
        mValueWrapper.setFloats(floats);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_doubleArrayList",threadModel = ThreadModel.POST)
    public void registerMethod36(ArrayList<Double> doubles) {
        Log.d(TAG,"Subscriber1_registerMethod29_doubleArrayList = " + doubles.toString());
        this.doubles = doubles;
        mValueWrapper.setDoubles(doubles);
    }

    @Subscriber(tag = "Subscriber1_registerMethod29_booleanArrayList",threadModel = ThreadModel.POST)
    public void registerMethod37(ArrayList<Boolean> booleans) {
        Log.d(TAG,"Subscriber1_registerMethod29_booleanArrayList = " + booleans.toString());
        this.booleans = booleans;
        mValueWrapper.setBooleans(booleans);
    }
}
