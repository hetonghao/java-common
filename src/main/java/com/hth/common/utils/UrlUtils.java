package com.hth.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Url
 *
 * @Author HeTongHao
 * @Date 2019/1/25 17:07
 */
public class UrlUtils {

    /**
     * 拼接url，防止出现多个/拼在一起的情况
     *
     * @param head  URL开头
     * @param items url多个拼接项
     * @return
     */
    public static String stitchingUrl(String head, final String... items) {
        if (head == null || items.length == 0) {
            return head;
        }
        for (String body : items) {
            if (StringUtils.isBlank(body)) {
                continue;
            }
            boolean headEndWithSeparator = head.lastIndexOf(File.separator) == head.length() - 1;
            boolean bodyFirstWithSeparator = body.indexOf(File.separator) == 0;
            if (headEndWithSeparator && bodyFirstWithSeparator) {
                head += body.substring(1);
            } else if (headEndWithSeparator || bodyFirstWithSeparator) {
                head += body;
            } else {
                head += File.separator + body;
            }
        }
        return head;
    }
}
