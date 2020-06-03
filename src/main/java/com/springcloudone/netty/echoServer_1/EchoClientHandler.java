package com.springcloudone.netty.echoServer_1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xw
 * @date 2019/12/3 15:13
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private int counter = 0;
    static final String encho_req = "你好，欢迎来到世界！$_";

    public EchoClientHandler(){}

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(encho_req.getBytes()));
        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("the counter is :" + ++counter + "; msg is :" + msg.toString());
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
