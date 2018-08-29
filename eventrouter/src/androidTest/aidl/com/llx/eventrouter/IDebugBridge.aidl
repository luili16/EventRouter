// IMyAidlInterface.aidl
package com.llx.eventrouter;

// Declare any non-default types here with import statements
import com.llx.eventrouter.enty.ValueWrapper;
interface IDebugBridge {
   ValueWrapper getVal();
   void setCmd(String cmd);
}