package com.hth.common.utils.time;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * 根据一个时间，转换成指定类型的开始时间~结束时间
 *
 * @author HeTongHao
 * @date 2018/12/28 15:35
 */
@Getter
@Setter
public class DateTimeSlot {
    private DateTimeSlot() {
    }

    /**
     * 时段类型
     * (1、日 2、月 3、年)
     */
    public static final Integer DAY = 1;
    public static final Integer MONTH = 2;
    public static final Integer YEAR = 3;

    private Date startDateTime;
    private Date endDateTime;
    /**
     * 该时段的时间范围（例如：2018、2018-11、2018-11-28）
     */
    private String dateTimeRangeText;

    /**
     * 转成一个时段
     *
     * @param timeSlotType 目标时段类型(1、日 2、月 3、年) 默认日
     * @param date         默认当前时间
     */
    public static DateTimeSlot getTimeSlot(@Nullable Integer timeSlotType, @Nullable Date date) {
        if (timeSlotType == null) {
            timeSlotType = DAY;
        }
        if (date == null) {
            date = new Date();
        }
        DateTimeSlot dateTimeSlot = new DateTimeSlot();
        //清除基础的时分秒
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CalendarUtils.clearHourAndMinuteAndSecondAndMillisecond(date));
        //年
        if (YEAR.equals(timeSlotType)) {
            //清除月
            calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.MONTH, 0);
            dateTimeSlot.setStartDateTime(calendar.getTime());
            dateTimeSlot.setEndDateTime(CalendarUtils.fillMonth(CalendarUtils.fillDay(CalendarUtils.fillHourAndMinuteAndSecondAndMillisecond(calendar.getTime()))));
            dateTimeSlot.setDateTimeRangeText(String.valueOf(calendar.get(Calendar.YEAR)));
            //月
        } else if (MONTH.equals(timeSlotType)) {
            //清除日
            calendar.set(Calendar.DATE, 1);
            dateTimeSlot.setStartDateTime(calendar.getTime());
            dateTimeSlot.setEndDateTime(CalendarUtils.fillDay(CalendarUtils.fillHourAndMinuteAndSecondAndMillisecond((calendar.getTime()))));
            dateTimeSlot.setDateTimeRangeText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1));
            //日
        } else if (DAY.equals(timeSlotType)) {
            dateTimeSlot.setStartDateTime(calendar.getTime());
            dateTimeSlot.setEndDateTime(CalendarUtils.fillHourAndMinuteAndSecondAndMillisecond(date));
            dateTimeSlot.setDateTimeRangeText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE));
        } else {
            throw new IllegalStateException("时段类型错误");
        }
        return dateTimeSlot;
    }
}
