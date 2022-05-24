package com.auth.utils;

import java.sql.Timestamp;

/**
 * Created by andriesholmes on 23/3/2017.
 */
    public class TimeUtil {

        public static Timestamp getTimeStamp(){
            return new Timestamp(System.currentTimeMillis());

            //Instant instant = Instant.now();
            //return new Timestamp(instant.toEpochMilli());
        }

    }
