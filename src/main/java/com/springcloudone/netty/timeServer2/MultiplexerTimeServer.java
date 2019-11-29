package com.springcloudone.netty.timeServer2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用类
 * 轮询 多路复用器 selctor
 * @author xw
 * @date 2019/11/28 11:21
 */
public class MultiplexerTimeServer implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    volatile boolean stop;

    public MultiplexerTimeServer(int port){
        try{
            // 打开多路复用器
            selector = Selector.open();
            // 打开ServerSocket通道
            serverSocketChannel = ServerSocketChannel.open();
            // 配置非阻塞
            serverSocketChannel.configureBlocking(false);
            // 绑定端口 设置大小
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 通道注册到复用器 监听accept位
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("绑定接口为：" + port);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        System.out.println("run");
        while (!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    System.out.println("ierator");
                    key = iterator.next();
                    iterator.remove();
                    try{
                        System.out.println("handleInput");
                        handleInput(key);
                    }catch (Exception e){
                        e.printStackTrace();
                        if (key != null){
                            key.cancel();
                            if (key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                SocketChannel sc = (SocketChannel) key.channel();
                int readBytes = sc.read(readBuffer);
                System.out.println(readBytes);
                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()]; // readBuffer.remaining() 获取缓存区可读字节的个数
                    readBuffer.get(bytes); // 将缓冲区可读的字节数组复制到新创建的直接数组中
                    String body = new String(bytes, "UTF-8");
                    System.out.println("接收到的内容是：" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ERROR";
                    doWrite(sc, currentTime);
                }else if (readBytes < 0){
                    // 关闭连接
                    key.cancel();
                    sc.close();
                }else {
                    // 0 忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String currentTime) throws IOException {
        if (currentTime != null && currentTime.trim().length() > 0){
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
