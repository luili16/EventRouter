package com.llx.eventrouter.enty;

import android.os.Parcel;
import android.os.Parcelable;

public class Event1 implements Parcelable {

    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
    }

    public Event1() {
    }

    protected Event1(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
    }

    public static final Parcelable.Creator<Event1> CREATOR = new Parcelable.Creator<Event1>() {
        @Override
        public Event1 createFromParcel(Parcel source) {
            return new Event1(source);
        }

        @Override
        public Event1[] newArray(int size) {
            return new Event1[size];
        }
    };

    @Override
    public String toString() {
        return "Event1{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
