package com.springcloudone.netty.timeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 处理链路线程
 * @author xw
 * @date 2019/11/27 16:31
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); // 输入流
            out = new PrintWriter(this.socket.getOutputStream(), true); // 输出流
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if (body == null) break;
                System.out.println("这个服务器接收的order:" + body);
                // 当接收的是 QUERY TIME ORDER 时 返回时间
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() :  "BAD ORDER";
                System.out.println(currentTime);
                // 将时间写入输出流
                out.println(currentTime);
            }
        }catch (Exception e){
            if (in != null){
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null){
                out.close();
                out = null;
            }
            if (this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
