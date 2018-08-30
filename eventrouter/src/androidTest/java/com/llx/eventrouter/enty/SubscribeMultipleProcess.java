package com.llx.eventrouter.enty;

import android.util.Log;

import com.llx.eventrouter.Subscriber;
import com.llx.eventrouter.execute.ThreadModel;

public class SubscribeMultipleProcess {

    public String msg;

    @Subscriber(tag = "remote_send",threadModel = ThreadModel.HANDLER)
    public void receiveMethod(String msg) {

        Log.d("main","receive msg : ---------- " + msg);
        this.msg = msg;


    }
}
