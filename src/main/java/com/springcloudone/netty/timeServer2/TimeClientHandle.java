package com.springcloudone.netty.timeServer2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xw
 * @date 2019/11/28 14:00
 */
public class TimeClientHandle implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {
        this.host = host != null ? host : "127.0.0.1";
        this.port = port;
//        this.stop = false;
        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        // 判断是否连接成功
        try{
            doConnect();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop){
             try{
                 selector.select(1000);
                 Set<SelectionKey> selectionKeys = selector.selectedKeys();
                 Iterator<SelectionKey> iterator = selectionKeys.iterator();
                 SelectionKey key = null;
                 while (iterator.hasNext()){
                     key = iterator.next();
                     iterator.remove();
                     try{
                         handleInput(key);
                     }catch (Exception e){
                         if (key != null){
                             key.cancel();
                             if (key.channel() != null){
                                 key.channel().close();
                             }
                         }
                     }
                 }
             }catch (Exception e){
                 e.printStackTrace();
                 System.exit(1);
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

    /**
     * 处理输入
     * @param key
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()){
                if (sc.finishConnect()){
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                }else {
                    System.exit(1);
                }
            }

            if (key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int read = sc.read(readBuffer);
                if (read > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);// 将缓冲流中的可读字节数组复制到新的字节数组（bytes）中
                    String BODY = new String(bytes, "UTF-8");
                    System.out.println("当前时间是："+BODY);
                    this.stop = true;
                }else if (read < 0){
                    key.cancel();
                    sc.close();
                }else {

                }
            }

        }
    }

    /**
     * 判断连接是否成功
     */
    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 向服务端发数据
     * @param socketChannel
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] bytes = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()){
            System.out.println("发送成功！");
        }
    }
}
