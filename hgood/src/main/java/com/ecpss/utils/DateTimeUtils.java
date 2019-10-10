package com.ecpss.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    static String DATE_FORMATER_1 = "yyyy-MM-dd";
    static String DATE_FORMATER_2 = "yyyyMMdd";
    static String DATETIME_FORMATER_1 = "yyyy-MM-dd HH:mm:ss";
    static String DATETIME_FORMATER_2 = "yyyyMMddHHmmss";


    public static String formatDateString() {
        return formatDateString(new Date(), DATE_FORMATER_1);
    }

    public static String formatDateString(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    public static String formatDateTimeString() {
        return formatDateTimeString(new Date(), DATETIME_FORMATER_1);
    }

    public static String formatDateTimeString(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    public static int compareDate(Date d1,Date d2){
        int compareResult=0;
        //id(d1<d2)compareResult = -1
        //id(d1=d2)compareResult = 0
        //id(d1>d2)compareResult = 1
        return compareResult;
    }

    public static Timestamp getCurrentTimestamp() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String evt_timestamp = tempDate.format(now.getTime());
        Timestamp timestatmp = null;
        try {
            timestatmp = Timestamp.valueOf(evt_timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return timestatmp;
    }

    public static String getCurrnetTime() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String evt_timestamp = tempDate.format(now.getTime());
        return evt_timestamp;
    }

}
