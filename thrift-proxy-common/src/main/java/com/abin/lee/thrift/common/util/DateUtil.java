package com.abin.lee.thrift.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: abin
 * Date: 16-4-28
 * Time: 下午1:10
 * To change this template use File | Settings | File Templates.
 * 线程安全的DateFormat
 */
public class DateUtil {
    private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    private static final String ymdhmsSdf = "yyyy-MM-dd HH:mm:ss";
    private static final String ymdSdf = "yyyy-MM-dd";
    private static final String hmsSdf = "HH:mm:ss";

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Date getYMDTime(String param) {
        Date result = null;
        try {
            result = DateFormatUtils.ISO_DATE_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Date getYMDHMSTime(String param) {
        Date result = null;
        try {
            result = FAST_DATE_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Date getHMSTime(String param) {
        Date result = null;
        try {
            result = DateFormatUtils.ISO_TIME_NO_T_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getYMDHMSTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDHMSTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), ymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getHMSTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), hmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getHMSTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, hmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    public static void main(String[] args) {
        String result = getYMDHMSTime();
        System.out.println("result="+result);
        String result1 = getYMDTime();
        System.out.println("result1="+result1);
        String result2 = getHMSTime();
        System.out.println("result2="+result2);
        Date result3 = getYMDTime("1999-02-15");
        System.out.println("result3="+result3);
        Date result4 = getHMSTime("12:00:13");
        System.out.println("result4="+result4);
    }

}
