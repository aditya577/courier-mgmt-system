package com.speedxcourier.SpeedX.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpeedXUtil {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DATE_YYYYMMDD_HHMMSS = "dd-M-yyyy hh:mm:ss";
    public static final String MESSAGE = "msg";
    public static final String STATUS_COLOR = "color";
    public static final String COLOR_SUCCESS = "green";
    public static final String COLOR_FAILURE = "red";


    public static boolean isEmpty(String str) {
        if (str == null) return true;
        else if (str.trim().equals("")) return true;
        return false;
    }

    public static Date getFormattedDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_YYYYMMDD_HHMMSS);
        Date dt = null;
        try {
            dt = sf.parse(sf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
    
}
