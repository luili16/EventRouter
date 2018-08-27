package com.llx.eventrouter;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * 代表一个订阅事件
 * 这里面传入的方法的参数class和tag作为这个Event的唯一标志
 */
public class Event implements Parcelable {

    private final String paramType;

    private final String tag;

    private final String returnType;

    public Event(@NonNull String paramType, @NonNull String tag, @NonNull String returnType) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!paramType.equals(event.paramType)) return false;
        if (!tag.equals(event.tag)) return false;
        return returnType.equals(event.returnType);
    }

    @Override
    public int hashCode() {
        int result = paramType.hashCode();
        result = 31 * result + tag.hashCode();
        result = 31 * result + returnType.hashCode();
        return result;
    }
}
