package com.ecpss.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lindongcheng on 14/10/28.
 */
public class DateUtils {

    public static Date getSetoffDay(int days) {
        return getSetoffDay(new Date(), days);
    }

    public static Date getSetoffDayTime(int days, int mins) {
        return getSetoffDayTime(new Date(), days, mins);
    }

    public static Date getSetoffDay(Date aDate, int days) {
        return new Date(aDate.getTime() + days * 24 * 60 * 60 * 1000l);
    }

    public static Date getSetoffDayTime(Date date, int days, int mins) {
        return new Date(date.getTime() + days * 24 * 60 * 60 * 1000l + mins * 60 * 1000l);
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat dateFormater1 = new SimpleDateFormat(formatStr);
        return dateFormater1.format(date);
    }

    public static Date getByOffset(int timeType, int timeValue) {
        switch (timeType) {
            case 0: //x天前
                return getSetoffDay(-timeValue);
            case 1: //x时前
                return getSetoffDayTime(new Date(), 0, -60 * timeValue);
            case 2: //xmin前
                return getSetoffDayTime(0, -timeValue);
            case 4: //x 秒前
                return new Date(new Date().getTime() - timeValue);
            default:
                throw new RuntimeException("无此时间类型");
        }
    }

    /**
     * 获取指定NG个月后的date
     */
    public static Date getSetoffMonth(Date aDate, int months) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(aDate);
        rightNow.add(Calendar.MONTH, months);
        return rightNow.getTime();
    }

    /**
     * 将字串转成日期和时间，字串格式: yyyy-MM-dd
     *
     * @param string
     * @return
     */
    public static Date toDate(String string) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(string);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将字串转成日期和时间，字串格式: yyyyMMddHHmmss
     *
     * @param string
     * @return
     */
    public static Date toDateTime(String string) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            return formatter.parse(string);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date toDateTime(String string, String formatterString) {
        try {
            DateFormat formatter = new SimpleDateFormat(formatterString);
            return formatter.parse(string);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取本月 开始 和 结束的 时间
     *
     * @return date[] 0:begin 1:end
     */
    public static Date[] getCurMonthBeginEndDate() {
        Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        /* 设置为当前时间 */
        cal.setTime(nowdate);
        /* 当前日期月份 + x */
        cal.add(Calendar.MONTH, 0);
        // 得到前一个月的第一天
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        String beginS = sdf.format(cal.getTime()) + "000000";
        // 得到前一个月的最后一天
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endS = sdf.format(cal.getTime()) + "235959";

        return new Date[]{toDateTime(beginS), toDateTime(endS)};
    }

    //------

    /**
     * 判断当前日期是星期几  String
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return dayForWeek(format.parse(pTime));
    }

    /**
     * 判断当前日期是星期几  data
     *
     * @param data 修要判断的时间
     * @return dayForWeek 判断结果
     */
    public static int dayForWeek(Date data) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断当前日期是否是所给星期   int(1-7)
     *
     * @param data 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static boolean isDayForWeek(Date data, int i) {
        if (dayForWeek(data) != i) {
            return false;
        }
        return true;
    }

    /**
     * 判断给定日期是否为月末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        /* int c=calendar.get(Calendar.DAY_OF_MONTH);*/
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断给定日期是否为年末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + 1));
        /*int c=calendar.get(Calendar.DAY_OF_YEAR);*/
        if (calendar.get(Calendar.DAY_OF_YEAR) == 1) {
            return true;
        }
        return false;
    }


    /**
     * 指定日期的开始时间
     *
     * @return
     */
    public static Date startForDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);


        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 指定日期的结束时间
     *
     * @return
     */
    public static Date endForDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 前天开始时间
     *
     * @return
     */
    public static Date startForBeforeDay() {
        return startForDay(getSetoffDay(new Date(), -1));
    }

    /**
     * 前天结束时间
     *
     * @return
     */
    public static Date endForBeforeDay() {
        return endForDay(getSetoffDay(new Date(), -1));
    }

    public static String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd");
        Date date = new Date();
        String time = "\\" + formatter.format(date) + "\\" + formatter1.format(date) + "\\" + formatter2.format(date) + "\\";
        return time;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 所给时间是否为当天
     *
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }
}
