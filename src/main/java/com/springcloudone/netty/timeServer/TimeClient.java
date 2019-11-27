package com.springcloudone.netty.timeServer;

import java.io.*;
import java.net.Socket;

/**
 * 同步阻塞式I/O
 * 时间客户端
 * @author xw
 * @date 2019/11/27 16:44
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            // 发送 QUERY TIME ORDER 到输出流中
            out.println("QUERY TIME ORDER");
            System.out.println("发送成功");
            // 读取输入流中的数据
            String resp = in.readLine();
            System.out.println("现在的时间是：" + resp);
        }catch (Exception e){

        }finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                out.close();
            }
        }

    }
}
