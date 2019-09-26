package com.springcloudone.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络编程utils
 * @author xw
 * @date 2019/9/19 14:36
 */
public class NetUtils {

    /**
     * 获取本地的IP地址
     * @return
     * @throws UnknownHostException
     */
    public static InetAddress getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }


    public static void main(String[] args) throws UnknownHostException {
        System.out.println(getLocalHost());
    }

}
