package com.hth.common.utils.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * java 1.8+ 时间对象（包括日历功能）帮助类,是安全的
 *
 * @Author HeTongHao
 * @Date 2019/3/12 18:47
 */
public class LocalDateTimeUtils {
    /**
     * 默认时区
     */
    private static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();


    /**
     * LocalDateTime转换Date
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(SYSTEM_ZONE_ID).toInstant());
    }

    /**
     * Date转换LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), SYSTEM_ZONE_ID);
    }

    /**
     * 设置一天的开始
     *
     * @param localDateTime
     * @return LocalDateTime
     */
    public static LocalDateTime setTheStartOfTheDay(final LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 设置一天的结束
     *
     * @param localDateTime
     * @return LocalDateTime
     */
    public static LocalDateTime setTheEndOfTheDay(final LocalDateTime localDateTime) {
        return localDateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 设置一天的开始
     *
     * @param date
     * @return Date
     */
    public static Date setTheStartOfTheDay(final Date date) {
        return toDate(setTheStartOfTheDay(toLocalDateTime(date)));
    }

    /**
     * 设置一天的结束
     *
     * @param date
     * @return Date
     */
    public static Date setTheEndOfTheDay(final Date date) {
        return toDate(setTheEndOfTheDay(toLocalDateTime(date)));
    }

    /**
     * 填满时间的月
     */
    public static Date fillMonth(final Date date) {
        return toDate(toLocalDateTime(date).withMonth(12));
    }

    /**
     * 填满时间的日
     */
    public static Date fillDay(final Date date) {
        return toDate(toLocalDateTime(date).with(TemporalAdjusters.lastDayOfMonth()));
    }
}
