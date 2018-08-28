package com.llx.eventrouter.enty;

import android.os.Parcel;
import android.os.Parcelable;

public class ValueWrapper implements Parcelable {

    private String tag = "";
    private int i;
    private long l;
    private char c;
    private byte b;
    private float f;
    private double d;
    private boolean bo;
    private Integer ii;
    private Long ll;
    private Byte bb;
    private Character cc;
    private Float ff;
    private Double dd;
    private String ss;
    private Boolean boo;
    private int[] is;
    private long[] ls;
    private char[] cs;
    private byte[] bs;
    private float[] fs;
    private double[] ds;
    private boolean[] bos;
    private String[] strs;
    private Event1 event;

    private String ready = "";

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public boolean isBo() {
        return bo;
    }

    public void setBo(boolean bo) {
        this.bo = bo;
    }

    public Integer getIi() {
        return ii;
    }

    public void setIi(Integer ii) {
        this.ii = ii;
    }

    public Long getLl() {
        return ll;
    }

    public void setLl(Long ll) {
        this.ll = ll;
    }

    public Byte getBb() {
        return bb;
    }

    public void setBb(Byte bb) {
        this.bb = bb;
    }

    public Character getCc() {
        return cc;
    }

    public void setCc(Character cc) {
        this.cc = cc;
    }

    public Float getFf() {
        return ff;
    }

    public void setFf(Float ff) {
        this.ff = ff;
    }

    public Double getDd() {
        return dd;
    }

    public void setDd(Double dd) {
        this.dd = dd;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public Boolean getBoo() {
        return boo;
    }

    public void setBoo(Boolean boo) {
        this.boo = boo;
    }

    public int[] getIs() {
        return is;
    }

    public void setIs(int[] is) {
        this.is = is;
    }

    public long[] getLs() {
        return ls;
    }

    public void setLs(long[] ls) {
        this.ls = ls;
    }

    public char[] getCs() {
        return cs;
    }

    public void setCs(char[] cs) {
        this.cs = cs;
    }

    public byte[] getBs() {
        return bs;
    }

    public void setBs(byte[] bs) {
        this.bs = bs;
    }

    public float[] getFs() {
        return fs;
    }

    public void setFs(float[] fs) {
        this.fs = fs;
    }

    public double[] getDs() {
        return ds;
    }

    public void setDs(double[] ds) {
        this.ds = ds;
    }

    public boolean[] getBos() {
        return bos;
    }

    public void setBos(boolean[] bos) {
        this.bos = bos;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public Event1 getEvent() {
        return event;
    }

    public void setEvent(Event1 event) {
        this.event = event;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeInt(this.i);
        dest.writeLong(this.l);
        dest.writeInt(this.c);
        dest.writeByte(this.b);
        dest.writeFloat(this.f);
        dest.writeDouble(this.d);
        dest.writeByte(this.bo ? (byte) 1 : (byte) 0);
        dest.writeValue(this.ii);
        dest.writeValue(this.ll);
        dest.writeValue(this.bb);
        dest.writeSerializable(this.cc);
        dest.writeValue(this.ff);
        dest.writeValue(this.dd);
        dest.writeString(this.ss);
        dest.writeValue(this.boo);
        dest.writeIntArray(this.is);
        dest.writeLongArray(this.ls);
        dest.writeCharArray(this.cs);
        dest.writeByteArray(this.bs);
        dest.writeFloatArray(this.fs);
        dest.writeDoubleArray(this.ds);
        dest.writeBooleanArray(this.bos);
        dest.writeStringArray(this.strs);
        dest.writeParcelable(this.event, flags);
        dest.writeString(this.ready);
    }

    public ValueWrapper() {
    }

    protected ValueWrapper(Parcel in) {
        this.tag = in.readString();
        this.i = in.readInt();
        this.l = in.readLong();
        this.c = (char) in.readInt();
        this.b = in.readByte();
        this.f = in.readFloat();
        this.d = in.readDouble();
        this.bo = in.readByte() != 0;
        this.ii = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ll = (Long) in.readValue(Long.class.getClassLoader());
        this.bb = (Byte) in.readValue(Byte.class.getClassLoader());
        this.cc = (Character) in.readSerializable();
        this.ff = (Float) in.readValue(Float.class.getClassLoader());
        this.dd = (Double) in.readValue(Double.class.getClassLoader());
        this.ss = in.readString();
        this.boo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.is = in.createIntArray();
        this.ls = in.createLongArray();
        this.cs = in.createCharArray();
        this.bs = in.createByteArray();
        this.fs = in.createFloatArray();
        this.ds = in.createDoubleArray();
        this.bos = in.createBooleanArray();
        this.strs = in.createStringArray();
        this.event = in.readParcelable(Event1.class.getClassLoader());
        this.ready = in.readString();
    }

    public static final Parcelable.Creator<ValueWrapper> CREATOR = new Parcelable.Creator<ValueWrapper>() {
        @Override
        public ValueWrapper createFromParcel(Parcel source) {
            return new ValueWrapper(source);
        }

        @Override
        public ValueWrapper[] newArray(int size) {
            return new ValueWrapper[size];
        }
    };
}
