package com.llx.eventrouter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 代表一个订阅事件
 * 这里面传入的方法的参数class和tag作为这个Event的唯一标志
 */
public class Event implements Parcelable {

    private String paramType;

    private String tag;

    private String returnType;

    public Event(String paramType, String tag, String returnType) {
        this.paramType = paramType;
        this.tag = tag;
        this.returnType = returnType;
    }

    protected Event(Parcel in) {
        paramType = in.readString();
        tag = in.readString();
        returnType = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getParamType() {
        return paramType;
    }

    public String getTag() {
        return tag;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "paramType='" + paramType + '\'' +
                ", tag='" + tag + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(paramType);
        dest.writeString(tag);
        dest.writeString(returnType);
    }
}
