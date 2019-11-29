package com.springcloudone.netty.timeServer4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * 客户端I/O事件处理器
 * @author xw
 * @date 2019/11/29 15:35
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private final ByteBuf firstMessage;

    /**
     * 初始化
     */
    public TimeClientHandler() {
        byte[] bytes = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送消息到服务器
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收服务器发送来的数据
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bs = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bs);
        String body = new String(bs, "utf-8");
        System.out.println("Now date is ：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexcepected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
