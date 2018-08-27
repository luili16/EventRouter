package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Event;
import com.llx.eventrouter.Subscriber;

import java.util.Arrays;

public class SubscriberBaseType {

    private static final String TAG = "SubscriberBaseType";

    int i;
    long l;
    char c;
    byte b;
    Integer ii;
    Long ll;
    Byte bb;
    int[] is;
    long[] ls;
    char[] cs;
    byte[] bs;
    Event1 event;

    @Subscriber(tag = "Subscriber1_registerMethod1_void")
    public void registerMethod1() {
        Log.d(TAG,"Subscriber1_registerMethod1_void");
    }

    @Subscriber(tag = "Subscriber1_registerMethod2_int")
    public void registerMethod2(int i) {
        Log.d(TAG,"Subscriber1_registerMethod2_int = " + i);
        this.i = i;
    }

    @Subscriber(tag = "Subscriber1_registerMethod3_long")
    public void registerMethod3(long l) {
        Log.e(TAG,"Subscriber1_registerMethod3_long = " + l);
        this.l = l;
    }

    @Subscriber(tag = "Subscriber1_registerMethod4_char")
    public void registerMethod4(char c) {
        Log.e(TAG,"Subscriber1_registerMethod4_char = " + String.valueOf(c));
        this.c = c;
    }

    @Subscriber(tag = "Subscriber1_registerMethod5_byte")
    public void registerMethod5(byte b) {
        Log.e(TAG,"Subscriber1_registerMethod5_byte = " + b);
        this.b = b;
    }

    @Subscriber(tag = "Subscriber1_registerMethod6_Integer")
    public void registerMethod6(Integer i) {
        Log.e(TAG,"Subscriber1_registerMethod6_Integer = " + i);
        this.ii = i;
    }

    @Subscriber(tag = "Subscriber1_registerMethod7_Long")
    public void registerMethod7(Long l) {
        Log.e(TAG,"Subscriber1_registerMethod7_Long = " + l);
        this.ll = l;
    }

    @Subscriber(tag = "Subscriber1_registerMethod8_Byte")
    public void registerMethod8(Byte b) {
        Log.e(TAG,"Subscriber1_registerMethod8_Byte = " + b);
        this.bb = b;
    }

    @Subscriber(tag = "Subscriber1_registerMethod9_int[]")
    public void registerMethod9(int[] is) {
        Log.e(TAG,"Subscriber1_registerMethod9_int[] = " + Arrays.asList(is));
        this.is = is;
    }

    @Subscriber(tag = "Subscriber1_registerMethod10_long[]")
    public void registerMethod10(long[] ls) {
        Log.e(TAG,"Subscriber1_registerMethod10_long[] = " + Arrays.asList(ls));
        this.ls = ls;
    }

    @Subscriber(tag = "Subscriber1_registerMethod11_char[]")
    public void registerMethod11(char[] cs) {
        Log.e(TAG,"Subscriber1_registerMethod11_char[] = " + Arrays.asList(cs));
        this.cs = cs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod12_byte[]")
    public void registerMethod12(byte[] bs) {
        Log.e(TAG,"Subscriber1_registerMethod12_byte[] = " + Arrays.asList(bs));
        this.bs = bs;
    }

    @Subscriber(tag = "Subscriber1_registerMethod13_parcelable")
    public void registerMethod13(Event1 event) {
        Log.e(TAG,"Subscriber1_registerMethod13_parcelable = " + event.toString());
        this.event = event;
    }
}
