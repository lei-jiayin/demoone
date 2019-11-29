package com.springcloudone.netty.timeServer3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author xw
 * @date 2019/11/29 10:18
 */
public class AsyncTimeServerHandler implements Runnable {

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel channel;

    /**
     * 初始化
     * @param port
     * @throws IOException
     */
    public AsyncTimeServerHandler(int port) throws IOException {
        this.port = port;
        this.channel = AsynchronousServerSocketChannel.open();
        channel.bind(new InetSocketAddress(port));
        System.out.println("端口绑定成功：" + port);
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        channel.accept(this, new AcceptCompletionHandler());
    }
}
