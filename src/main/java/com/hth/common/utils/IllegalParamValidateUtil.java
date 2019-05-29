package com.hth.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 非法参数验证,验证不通过直接抛出IllegalArgumentException
 *
 * @author HeTongHao
 * @version 2018-7-13
 */
public class IllegalParamValidateUtil {
    /**
     * 验证值是否在范围内
     *
     * @param message 异常消息
     * @param val     值
     * @param min     最小
     * @param max     最大
     */
    public static void range(String message, Integer val, Integer min, Integer max) {
        notNull(message, val);
        if (val < min || val > max) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 验证值是否在范围内
     *
     * @param message 异常消息
     * @param val     值
     * @param min     最小
     * @param max     最大
     */
    public static void range(String message, BigDecimal val, BigDecimal min, BigDecimal max) {
        notNull(message, val);
        if (val.doubleValue() < min.doubleValue() || val.doubleValue() > max.doubleValue()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 验证值是否符合最小值
     *
     * @param message 异常消息
     * @param val     值
     * @param min     最小
     */
    public static void min(String message, Integer val, Integer min) {
        notNull(message, val);
        if (val < min) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 验证值是否符合最大值
     *
     * @param message 异常消息
     * @param val     值
     * @param max     最大
     */
    public static void max(String message, Integer val, Integer max) {
        notNull(message, val);
        if (val > max) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 验证非空
     *
     * @param message 异常消息
     * @param objS    可传多个对象依次判断
     */
    public static void notNull(String message, Object... objS) {
        for (Object obj : objS) {
            if (obj == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * 验证非空字符串
     *
     * @param message 异常消息
     * @param strS    可传多个String依次判断
     */
    public static void notBlank(String message, String... strS) {
        for (String str : strS) {
            if (StringUtils.isBlank(str)) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
