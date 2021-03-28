package com.github.saschas93.simpleblockchain.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampUtil {
    
    public static Timestamp getNow() {
        return new Timestamp(new Date().getTime());
    }

}
