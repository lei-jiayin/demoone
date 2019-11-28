package com.springcloudone.netty.timeServer1;

import com.springcloudone.netty.timeServer.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪 异步 I/O 线程池实现
 * 时间服务器
 * @author xw
 * @date 2019/11/28 9:38
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            Socket socket = null;
            System.out.println("伪异步I/O时间服务器绑定端口：" + port);
            // 创建 I/O 任务线程池
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50,10000);

            while(true){
                socket = server.accept();
                // 新建线程 放入线程池中
                executePool.execute(new TimeServerHandler(socket));
            }

        }catch (Exception e){

        }finally {
            if (server != null){
                server.close();
                server = null;
            }
        }
    }
}
