package com.springcloudone.netty.timeServer4_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * 客户端I/O事件处理器
 * 未考虑TCP粘包导致功能异常案例
 * @author xw
 * @date 2019/11/29 15:35
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;
    private byte[] req;

//    private final ByteBuf firstMessage;

    /**
     * 初始化
     */
    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            // 发送消息到服务器
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收服务器发送来的数据
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] bs = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bs);
//        String body = new String(bs, "utf-8");
        String body = (String) msg;
        System.out.println("Now date is ：" + body + "the counter is :" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexcepected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
