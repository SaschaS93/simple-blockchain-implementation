package com.github.szsascha.simpleblockchain.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampUtil {
    
    public static Timestamp getNow() {
        return new Timestamp(new Date().getTime());
    }

}
