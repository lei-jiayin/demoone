package com.springcloudone.thread.test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试之原子类的不安全性
 * @author xw
 * @date 2019/11/20 10:14
 */
public class MyService {
    public AtomicLong aiRef = new AtomicLong();
//    public static AtomicLong aiRef = new AtomicLong();
    // synchronized 关键字
/*    public synchronized void addNum(){
        System.out.println(Thread.currentThread().getName() + "加了100之后的值是：" + aiRef.addAndGet(100));
        aiRef.addAndGet(1);
    }*/
    Lock lock = new ReentrantLock();
    /**
     * lock
     */
    public void addNum(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "加了100之后的值是：" + aiRef.addAndGet(100));
            aiRef.addAndGet(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
