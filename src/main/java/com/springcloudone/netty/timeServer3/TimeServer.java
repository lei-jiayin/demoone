package com.springcloudone.netty.timeServer3;

import java.io.IOException;

/**
 * AIO 实现 时间服务器
 * @author xw
 * @date 2019/11/29 10:15
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(asyncTimeServerHandler, "AIO-AsyncTimeServerHandler-001").start();
    }
}
