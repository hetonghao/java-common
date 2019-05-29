package com.hth.common.utils.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @Author HeTongHao
 * @Date 2019/1/9 16:27
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    public static Date formatDateToDate() {
        return parseDate(getDate("yyyy-MM-dd"));
    }

    public static Date formatDateToDate(Date date) {
        return parseDate(DateFormatUtils.format(date, "yyyy-MM-dd"));
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前日期与传入日期还差多少数
     *
     * @param date
     * @return
     */
    public static long differDays(Date date) {
        long t = date.getTime() - new Date().getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    public static long differRealDays(Date date) {
        long t = parseDate(formatDate(new Date())).getTime() - parseDate(formatDate(date)).getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时数
     *
     * @param date
     * @return
     */
    public static long pastHours(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * 毫秒转化
     */
    public static String formatTime(Long ms) {
        if (ms == null) {
            return "刚刚";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        //		long milliSecond = ms - day * dd - hour * hh - minute * mi - second
        //				* ss;

        StringBuffer dateStr = new StringBuffer();
        if (day > 0) {
            dateStr.append(day).append("天前");
        } else if (hour > 0) {
            dateStr.append(hour).append("小时前");
        } else if (minute > 0) {
            dateStr.append(minute).append("分钟前");
        } else if (second > 0) {
            dateStr.append(second).append("秒前");
        }
        if (StringUtils.isEmpty(dateStr)) {
            dateStr.append("刚刚");
        }
        //		String strDay = day < 10 ? "0" + day : "" + day; // 天
        //		String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        //		String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        //		String strSecond = second < 10 ? "0" + second : "" + second;// 秒
        //		String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : ""
        //				+ milliSecond;// 毫秒
        //		strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : ""
        //				+ strMilliSecond;


        return dateStr.toString();
    }

    /**
     * 秒格式化输出
     *
     * @param second
     * @return
     */
    public static String formatSecond(Long second) {
        long day1 = second / (24 * 3600);
        long hour1 = second % (24 * 3600) / 3600;
        long minute1 = second % 3600 / 60;
        long second1 = second % 60;
        StringBuilder result = new StringBuilder("");
        if (day1 > 0) {
            result.append(day1).append("天");
        }
        if (hour1 > 0) {
            result.append(hour1).append("小时");
        }
        if (minute1 > 0) {
            result.append(minute1).append("分");
        }
        if (second1 > 0) {
            result.append(second1).append("秒");
        }
        return result.toString();
    }

    /**
     * 分钟格式输出
     *
     * @param second
     * @return
     */
    public static String formatMinute(Long minute) {
        return formatSecond(minute * 60);
    }

    /**
     * 设置当天结束时间
     *
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 设置当天开始时间
     *
     * @param date
     * @return
     */
    public static Date setBeginDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(differRealDays(parseDate("2017-03-25 12:11:01")));
    }
}
