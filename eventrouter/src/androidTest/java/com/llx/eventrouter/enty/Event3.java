package com.llx.eventrouter.enty;

import android.os.Parcel;
import android.os.Parcelable;

public class Event3 implements Parcelable {

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

    public Event3() {
    }

    protected Event3(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
    }

    public static final Creator<Event3> CREATOR = new Creator<Event3>() {
        @Override
        public Event3 createFromParcel(Parcel source) {
            return new Event3(source);
        }

        @Override
        public Event3[] newArray(int size) {
            return new Event3[size];
        }
    };
}
