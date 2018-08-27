// IReceiver.aidl
package com.llx.eventrouter.remote;

import android.os.Bundle;
interface IReceiver {
    //当一个进程收到了这个消息，会通过此方法回调
    void onMessageReceive(String fromAddress,in Bundle message);
}
