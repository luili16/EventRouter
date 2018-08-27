package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Subscriber;
import com.llx.eventrouter.execute.ThreadModel;

import java.util.Arrays;

public class SubscriberBaseTypeMirror {

    private static final String TAG = "SubscriberParamType";

    public int i;
    public long l;
    public char c;
    public byte b;
    public float f;
    public double d;
    public Integer ii;
    public Long ll;
    public Byte bb;
    public Character cc;
    public Float ff;
    public Double dd;
    public int[] is;
    public long[] ls;
    public char[] cs;
    public byte[] bs;
    public float[] fs;
    public double[] ds;
    public Event1 event;

    @Subscriber(tag = "Subscriber1_registerMethod1_void")
    public void registerMethod1() {
        Log.d(TAG, "Subscriber1_registerMethod1_void");
    }

    // ----- int Integer 相同 ------
    @Subscriber(tag = "Subscriber1_registerMethod2_int", threadModel = ThreadModel.POST)
    public void registerMethod2(int i) {
        Log.d(TAG, "Subscriber1_registerMethod2_int = " + i);
        this.i = i;
    }

    @Subscriber(tag = "Subscriber1_registerMethod2_int", threadModel = ThreadModel.POST)
    public void registerMethod6(Integer i) {
        Log.d(TAG, "Subscriber1_registerMethod_Integer = " + i);
        this.ii = i;
    }
    // ----- int Integer 相同 ------

    // ----- long Long 相同 -------
    @Subscriber(tag = "Subscriber1_registerMethod3_long", threadModel = ThreadModel.POST)
    public void registerMethod3(long l) {
        Log.d(TAG, "Subscriber1_registerMethod3_long = " + l);
        this.l = l;
    }

    @Subscriber(tag = "Subscriber1_registerMethod3_long", threadModel = ThreadModel.POST)
    public void registerMethod7(Long l) {
        Log.d(TAG, "Subscriber1_registerMethod7_Long = " + l);
        this.ll = l;
    }
    // ----- long Long 相同 ----------

    // ----- char character 相同 ----------
    @Subscriber(tag = "Subscriber1_registerMethod4_char", threadModel = ThreadModel.POST)
    public void registerMethod4(char c) {
        Log.d(TAG, "Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.c = c;
    }

    @Subscriber(tag = "Subscriber1_registerMethod4_char", threadModel = ThreadModel.POST)
    public void registerMethod14(Character c) {
        Log.d(TAG, "Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.cc = c;
    }
    // ---- char character is same

    // ----- byte Byte is same ------
    @Subscriber(tag = "Subscriber1_registerMethod5_byte", threadModel = ThreadModel.POST)
    public void registerMethod5(byte b) {
        Log.d(TAG, "Subscriber1_registerMethod5_byte = " + b);
        this.b = b;
    }

    @Subscriber(tag = "Subscriber1_registerMethod5_byte", threadModel = ThreadModel.POST)
    public void registerMethod8(Byte b) {
        Log.d(TAG, "Subscriber1_registerMethod8_Byte = " + b);
        this.bb = b;
    }
    // ---- byte Byte is same -----------------

    @Subscriber(tag = "Subscriber1_registerMethod19_float",threadModel = ThreadModel.POST)
    public void registerMethod19(float f) {
        Log.d(TAG,"Subscriber1_registerMethod19_float = " + f);
        this.f = f;
    }

    @Subscriber(tag = "Subscriber1_registerMethod19_float",threadModel = ThreadModel.POST)
    public void registerMethod20(Float f) {
        Log.d(TAG,"Subscriber1_registerMethod19_float = " + f);
        this.ff = f;
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_double",threadModel = ThreadModel.POST)
    public void registerMethod21(double f) {
        Log.d(TAG,"Subscriber1_registerMethod21_double = " + f);
        this.d = f;
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_double",threadModel = ThreadModel.POST)
    public void registerMethod22(Double f) {
        Log.d(TAG,"Subscriber1_registerMethod21_Double = " + f);
        this.dd = f;
    }

    // ---- Integer[] can not support
    @Subscriber(tag = "Subscriber1_registerMethod9_int[]", threadModel = ThreadModel.POST)
    public void registerMethod9(int[] is) {
        Log.d(TAG, "Subscriber1_registerMethod9_int[] = " + Arrays.asList(is));
        this.is = is;
    }


    // ---- Long[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod10_long[]", threadModel = ThreadModel.POST)
    public void registerMethod10(long[] ls) {
        Log.d(TAG, "Subscriber1_registerMethod10_long[] = " + Arrays.asList(ls));
        this.ls = ls;
    }

    // ---- Character[] can't support-------
    @Subscriber(tag = "Subscriber1_registerMethod11_char[]", threadModel = ThreadModel.POST)
    public void registerMethod11(char[] cs) {
        Log.d(TAG, "Subscriber1_registerMethod11_char[] = " + Arrays.asList(cs));
        this.cs = cs;
    }

    // ---- Byte[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod12_byte[]", threadModel = ThreadModel.POST)
    public void registerMethod12(byte[] bs) {
        Log.d(TAG, "Subscriber1_registerMethod12_byte[] = " + Arrays.asList(bs));
        this.bs = bs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod23_float[]",threadModel = ThreadModel.POST)
    public void registerMethod23(float[] fs) {
        Log.d(TAG,"Subscriber1_registerMethod23_float");
        this.fs = fs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod24_double[]",threadModel = ThreadModel.POST)
    public void registerMethod24(double[] ds) {
        Log.d(TAG,"Subscriber1_registerMethod24_double[]");
        this.ds = ds;
    }

    // ---- Byte[] can't support

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelable", threadModel = ThreadModel.POST)
    public void registerMethod13(Event1 event) {
        Log.e(TAG, "Subscriber1_registerMethod13_parcelable = " + event.toString());
        this.event = event;
    }
}
