package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Subscriber;
import com.llx.eventrouter.Type;
import com.llx.eventrouter.execute.ThreadModel;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Arrays;

public class SubscriberReturnType {

    private static final String TAG = "SubscriberParamType";

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
    public String s;
    public int[] is;
    public long[] ls;
    public char[] cs;
    public byte[] bs;
    public float[] fs;
    public double[] ds;
    public boolean[] boo;
    public String[] ss;
    public Event1 event;
    public ArrayList<Event1> event1s;

    public ArrayList<Integer> integers;
    public ArrayList<Long> longs;
    public ArrayList<Character> characters;
    public ArrayList<Byte> bytes;
    public ArrayList<Float> floats;
    public ArrayList<Double> doubles;
    public ArrayList<Boolean> booleans;

    public SubscriberReturnType() {
    }

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

    @Subscriber(tag = "Subscriber1_registerMethod27_boolean", threadModel = ThreadModel.HANDLER,type = Type.BLOCK)
    public boolean registerMethod27(boolean b) {
        Log.d(TAG,"Subscriber1_registerMethod27_boolean = " + b);
        this.bo = b;
        return b;
    }

    @Subscriber(tag = "Subscriber1_registerMethod26_String",type = Type.BLOCK)
    public String registerMethod26(String s) {
        Log.d(TAG,"Subscriber1_registerMethod26_String = " + s);
        this.s = s;
        return s;
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

    @Subscriber(tag = "Subscriber1_registerMethod28_boolean[]",type = Type.BLOCK)
    public boolean[] registerMethod28(boolean[] bs) {
        Log.d(TAG,"Subscriber1_registerMethod24_boolean[]");
        this.boo = bs;
        return bs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod25_String[]",type = Type.BLOCK)
    public String[] retisterMethod25(String[] ss) {
        Log.d(TAG,"Subscriber1_registerMethod25_String[]");
        this.ss = ss;
        return ss;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelable", threadModel = ThreadModel.POST,type = Type.BLOCK)
    public Event1 registerMethod13(Event1 event) {
        Log.e(TAG, "Subscriber1_registerMethod13_parcelable = " + event.toString());
        this.event = event;
        return event;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelableArrayList",threadModel = ThreadModel.POST,type = Type.BLOCK)
    public ArrayList<Event1> registerMethod29(ArrayList<Event1> event1s) {
        Log.d(TAG,"Subscriber1_registerMethod13_parcelableArrayList = " + event1s.toString());
        this.event1s = event1s;
        return event1s;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_integerArrayList",threadModel = ThreadModel.HANDLER,type = Type.BLOCK)
    public ArrayList<Integer> registerMethod30(ArrayList<Integer> integers) {
        Log.d(TAG,"Subscriber1_registerMethod13_integerArrayList = " + integers.toString());
        this.integers = integers;
        return integers;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_longArrayList",threadModel = ThreadModel.MAIN,type = Type.BLOCK)
    public ArrayList<Long> registerMethod31(ArrayList<Long> longs) {
        this.longs = longs;
        return longs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_characterArrayList",threadModel = ThreadModel.HANDLER,type = Type.BLOCK)
    public ArrayList<Character> registerMethod32(ArrayList<Character> characters) {
        this.characters = characters;
        return characters;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_bytesArrayList",threadModel = ThreadModel.POOL,type = Type.BLOCK)
    public ArrayList<Byte> registerMethod33(ArrayList<Byte> bytes) {
        this.bytes = bytes;
        return bytes;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_floatArrayList",threadModel = ThreadModel.HANDLER,type = Type.BLOCK)
    public ArrayList<Float> registerMethod34(ArrayList<Float> floats) {
        this.floats = floats;
        return floats;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_doubleArrayList",threadModel = ThreadModel.POST,type = Type.BLOCK)
    public ArrayList<Double> registerMethod35(ArrayList<Double> doubles) {
        this.doubles = doubles;
        return doubles;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_booleanArrayList",threadModel = ThreadModel.POST,type = Type.BLOCK)
    public ArrayList<Boolean> registerBoolean(ArrayList<Boolean> booleans) {
        this.booleans = booleans;
        return booleans;
    }
}
