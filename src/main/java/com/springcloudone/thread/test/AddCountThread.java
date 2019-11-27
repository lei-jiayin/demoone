package com.springcloudone.thread.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程测试之 原子类 AtomicInteger
 * @author xw
 * @date 2019/11/20 10:04
 */
public class AddCountThread extends Thread {

    private AtomicInteger count = new AtomicInteger(0);
    @Override
    public void run() {
        for(int i = 0; i < 10000; i++){
            System.out.println(count.incrementAndGet());
        }
    }
}
