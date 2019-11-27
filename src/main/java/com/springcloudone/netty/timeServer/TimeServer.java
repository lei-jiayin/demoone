package com.springcloudone.netty.timeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞式I/O
 * 时间服务器
 * @author xw
 * @date 2019/11/27 16:23
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port); // 绑定端口
            System.out.println("时间服务器启动端口为" + port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept(); // 生成套接字
                new Thread(new TimeServerHandler(socket)).start(); // 每当有客户端进来时分配线程 处理请求
            }
        }finally {
            if (serverSocket != null){
                System.out.println("关闭时间服务器");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }

}
