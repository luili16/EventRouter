package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Subscriber;
import com.llx.eventrouter.Type;
import com.llx.eventrouter.execute.ThreadModel;

import java.lang.annotation.Retention;
import java.util.Arrays;

public class SubscriberReturnType {

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

    @Subscriber(tag = "Subscriber1_registerMethod2_int", type = Type.BLOCK, threadModel = ThreadModel.POST)
    public int registerMethod2(int i) {
        Log.d(TAG, "Subscriber1_registerMethod2_int = " + i);
        this.i = i;
        return i;
    }

    @Subscriber(tag = "Subscriber1_registerMethod3_long", threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public long registerMethod3(long l) {
        Log.d(TAG, "Subscriber1_registerMethod3_long = " + l);
        this.l = l;
        return l;
    }

    // ----- char character 相同 ----------
    @Subscriber(tag = "Subscriber1_registerMethod4_char", threadModel = ThreadModel.POST,type = Type.BLOCK)
    public char registerMethod4(char c) {
        Log.d(TAG, "Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.c = c;
        return c;
    }

    // ---- char character is same

    // ----- byte Byte is same ------
    @Subscriber(tag = "Subscriber1_registerMethod5_byte", threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public byte registerMethod5(byte b) {
        Log.d(TAG, "Subscriber1_registerMethod5_byte = " + b);
        this.b = b;
        return b;
    }
    // ---- byte Byte is same -----------------

    @Subscriber(tag = "Subscriber1_registerMethod19_float",threadModel = ThreadModel.POST,type = Type.BLOCK)
    public float registerMethod19(float f) {
        Log.d(TAG,"Subscriber1_registerMethod19_float = " + f);
        this.f = f;
        return f;
    }

    @Subscriber(tag = "Subscriber1_registerMethod21_double",threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public double registerMethod21(double f) {
        Log.d(TAG,"Subscriber1_registerMethod21_double = " + f);
        this.d = f;
        return f;
    }


    // ---- Integer[] can not support
    @Subscriber(tag = "Subscriber1_registerMethod9_int[]", threadModel = ThreadModel.MAIN,type = Type.BLOCK)
    public int[] registerMethod9(int[] is) {
        Log.d(TAG, "Subscriber1_registerMethod9_int[] = " + Arrays.asList(is));
        this.is = is;
        return is;
    }


    // ---- Long[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod10_long[]", threadModel = ThreadModel.POST,type = Type.BLOCK)
    public long[] registerMethod10(long[] ls) {
        Log.d(TAG, "Subscriber1_registerMethod10_long[] = " + Arrays.asList(ls));
        this.ls = ls;
        return ls;
    }

    // ---- Character[] can't support-------
    @Subscriber(tag = "Subscriber1_registerMethod11_char[]", threadModel = ThreadModel.HANDLER,type = Type.BLOCK)
    public char[] registerMethod11(char[] cs) {
        Log.d(TAG, "Subscriber1_registerMethod11_char[] = " + Arrays.asList(cs));
        this.cs = cs;
        return cs;
    }

    // ---- Byte[] can't support
    @Subscriber(tag = "Subscriber1_registerMethod12_byte[]", threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public byte[] registerMethod12(byte[] bs) {
        Log.d(TAG, "Subscriber1_registerMethod12_byte[] = " + Arrays.asList(bs));
        this.bs = bs;
        return bs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod23_float[]",threadModel = ThreadModel.MAIN,type = Type.BLOCK)
    public float[] registerMethod23(float[] fs) {
        Log.d(TAG,"Subscriber1_registerMethod23_float");
        this.fs = fs;
        return fs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod24_double[]",threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public double[] registerMethod24(double[] ds) {
        Log.d(TAG,"Subscriber1_registerMethod24_double[]");
        this.ds = ds;
        return ds;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelable", threadModel = ThreadModel.POST,type = Type.BLOCK)
    public Event1 registerMethod13(Event1 event) {
        Log.e(TAG, "Subscriber1_registerMethod13_parcelable = " + event.toString());
        this.event = event;
        return event;
    }
}
