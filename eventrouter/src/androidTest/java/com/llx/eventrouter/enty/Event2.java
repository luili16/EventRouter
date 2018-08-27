package com.llx.eventrouter.enty;

import android.os.Parcel;
import android.os.Parcelable;

public class Event2 implements Parcelable {

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

    public Event2() {
    }

    protected Event2(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
    }

    public static final Creator<Event2> CREATOR = new Creator<Event2>() {
        @Override
        public Event2 createFromParcel(Parcel source) {
            return new Event2(source);
        }

        @Override
        public Event2[] newArray(int size) {
            return new Event2[size];
        }
    };
}
