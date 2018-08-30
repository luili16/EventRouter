package com.llx.eventrouter.remote;

import android.content.Context;
import android.os.Process;
import android.util.Log;

/**
 * 代表一个进程的地址
 */
public class Address {

    private int mPid;

    private static final int sBroadAddress = -100;

    private Address(int pid) {
        mPid = pid;
    }

    /**
     * 生成本进程的地址
     * @return 本进程的地址
     */
    public static Address toAddress() {
        return new Address(Process.myPid());
    }

    /**
     * 解析地址
     * @param address 地址名称
     *
     * @return 生成的地址
     */
    static Address toAddress(String address) {

        String[] split = address.split(":");
        if (split.length != 2) {
            return null;
        }

        int pid;
        try {
            pid = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            Log.e("Address", String.format("illegal address(%s)!", address));
            return null;
        }

        return new Address(pid);
    }

    public static Address toAddress(int pid) {
        return new Address(pid);
    }

    /**
     * 由processName解析出地址
     * @param context context
     * @param processName processName
     * @return
     */
    public static Address toAddress(Context context, String processName) {
        return null;
    }

    @Override
    public String toString() {
        return "pid:"+mPid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return mPid == address.mPid;
    }

    @Override
    public int hashCode() {
        return mPid;
    }
}
