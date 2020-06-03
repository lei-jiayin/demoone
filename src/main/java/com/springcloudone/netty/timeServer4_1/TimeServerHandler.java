package com.springcloudone.netty.timeServer4_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * netty 服务器处理类
 * 未考虑TCP粘包导致功能异常案例
 * @author xw
 * @date 2019/11/29 14:31
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    /**
     * 计数
     */
    private int counter;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // 类型转换
//        ByteBuf byteBuf = (ByteBuf) msg;
        // 创建新的字节数组 将缓冲流中的数据放入数组中
//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
        //
//        String body = new String(req, "utf-8").substring(0,req.length - System.getProperty("line.separator").length());
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + "; The counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "bad order";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        // 异步发送消息 此时消息在缓冲数组中
        ctx.writeAndFlush(resp);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将缓冲区中的消息 写入 SocketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常关闭 释放ChannelHandlerContext相关联的句柄等资源
        ctx.close();
    }
}
