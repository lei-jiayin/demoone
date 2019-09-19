package com.springcloudone.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络编程utils
 * @author xw
 * @date 2019/9/19 14:36
 */
public class NetUtils {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
    }

}
