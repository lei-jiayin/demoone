package com.springcloudone.netty.echoServer_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * echo服务器 数据处理
 * DelimiterBasedFrameDecoder
 * @author xw
 * @date 2019/12/3 14:44
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    // 计数器
    int counter = 0;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("the counter is : " + ++counter + "; 接收到的数据：" + body);
        body = body + "_xw_" + "$_";
        ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
