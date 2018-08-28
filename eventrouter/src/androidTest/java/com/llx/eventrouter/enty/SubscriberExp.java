package com.llx.eventrouter.enty;

import android.os.Bundle;
import android.os.Parcelable;

import com.llx.eventrouter.Subscriber;

public class SubscriberExp {

    @Subscriber(tag = "SubscriberExp_integer_array")
    public void testInteger(Integer[] is) {

    }

    @Subscriber(tag = "SubscriberExp_long_array")
    public void testLong(Long[] ls) {

    }

    @Subscriber(tag = "SubscriberExp_float_array")
    public void testFloat(Float[] fs) {

    }

    @Subscriber(tag = "SubscriberExp_double_array")
    public void testDouble(Double[] ds) {

    }

    @Subscriber(tag = "SubscriberExp_character_array")
    public void testCharacter(Character[] cs) {

    }

    @Subscriber(tag = "SubscriberExp_byte_array")
    public void testByte(Byte[] cs) {

    }

    @Subscriber(tag = "SubscriberExp_parcelable_array")
    public void testParcelable(Parcelable[] ps) {

    }

    @Subscriber(tag = "SubscriberExp_object_array")
    public void testObject(Object[] objs) {
    }

}
