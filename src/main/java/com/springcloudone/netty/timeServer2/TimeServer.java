package com.springcloudone.netty.timeServer2;

/**
 * NIO 实现 时间服务器
 * @author xw
 * @date 2019/11/28 11:20
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server, "NIO-MultiplexerTimeServer-001").start();
    }
}
