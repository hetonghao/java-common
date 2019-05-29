package com.hth.common.utils.time;

import java.util.Calendar;
import java.util.Date;

/**
 * 日历帮助类，日历是不安全的,所以用synchronized来保证原子性、可见性
 *
 * @Author HeTongHao
 * @Date 2019/1/9 16:27
 */
public class CalendarUtils {
    private static final Calendar calendar = Calendar.getInstance();

    /**
     * 清除时间的时分秒
     */
    public static Date clearHourAndMinuteAndSecondAndMillisecond(final Date date) {
        synchronized (calendar) {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
    }

    /**
     * 填满时间的时分秒
     */
    public static Date fillHourAndMinuteAndSecondAndMillisecond(final Date date) {
        synchronized (calendar) {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            return calendar.getTime();
        }
    }

    /**
     * 填满时间的月
     */
    public static Date fillMonth(final Date date) {
        synchronized (calendar) {
            calendar.setTime(date);
            // 设为12月
            calendar.set(Calendar.MONTH, 11);
            return calendar.getTime();
        }
    }

    /**
     * 填满时间的日
     */
    public static Date fillDay(final Date date) {
        synchronized (calendar) {
            calendar.setTime(date);
            calendar.set(Calendar.DATE, 1);
            // 加一月再减一天为当月最后一天
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            return calendar.getTime();
        }
    }

    /**
     * 获取某一天的0时0分(0则为今日，-1为昨日以此类推)
     *
     * @param offsetDay
     * @return
     */
    public static Date obtainDate(Integer offsetDay) {
        synchronized (calendar) {
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, offsetDay);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
    }
}
