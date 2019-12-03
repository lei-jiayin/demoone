package com.springcloudone.netty.timeServer4_1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty 实现时间服务器
 * 未考虑TCP粘包导致功能异常案例
 * @author xw
 * @date 2019/11/29 14:15
 */
public class TimeServer {

    /**
     * 绑定端口 初始化
     * @param port
     * @throws Exception
     */
    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            // 用于启动NIO的辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 加入ServerBootstrap中
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());// 绑定I/O事件处理器
            // 绑定端口 同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();

            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }catch (Exception e){
            // 退出 释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }
    public static void main(String[] args) throws Exception {
        int port = 8080;
        new TimeServer().bind(port);
    }
}
