package com.hth.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author HeTongHao
 * @date 2019/5/29 17:16
 */
public class IPUtil {

    /**
     * 从request获取IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(ServletRequest request) {
        HttpServletRequest localRequest = (HttpServletRequest) request;
        String ip = localRequest.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = localRequest.getHeader("X-Forwarded-For");
            if (StringUtils.isNotBlank(ip)) {
                String[] ipArray = ip.split(",");
                //不止一个ip
                if (ipArray.length > 1) {
                    //取第一个为真实ip
                    ip = ipArray[0].trim();
                }
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = localRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String[] getDomainIP(String domain) throws UnknownHostException {
        InetAddress[] array = InetAddress.getAllByName(domain);
        String ips[] = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            ips[i] = array[i].getHostAddress();
        }
        return ips;
    }

    /**
     * 得到本机ip
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalIP() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        //获得本机IP
        return inetAddress.getHostAddress();
    }

}
