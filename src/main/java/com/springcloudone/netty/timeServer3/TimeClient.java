package com.springcloudone.netty.timeServer3;

import java.io.IOException;

/**
 * @author xw
 * @date 2019/11/29 11:24
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port),"AIO-AsyncTimeClientHandler-001").start();
    }
}
