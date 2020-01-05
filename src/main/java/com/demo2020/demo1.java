package com.demo2020;


import java.util.concurrent.TimeUnit;

class MyData{
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    public void addPlusPlus(){
        number++;
    }
}

/**
 * voliatile 原子可见性
 * voliatile 不保证原子性
 * @author xw
 * @date 2020/1/1 17:38
 */
public class demo1 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i < 20; i++){
            new Thread(() -> {
                for (int j = 0; j <= 1000; j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        /*try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number
        );

    }

    /**
     * 1 验证voliatile的可见性
     * 加入 voliatile 可以保证公共变量的可见性
     *
     * 2 原子性 即某个线程在执行具体业务时 中间不可以被加塞或被分割 要么一起成功要么一起失败
     */
    private static void seeOkByVoliatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "/t come in");
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "/t updated number to :" + myData.number);
        }, "AAA").start();

        while (myData.number == 0){
            // 一直循环
        }

        System.out.println(Thread.currentThread().getName() + "/t get number = " + myData.number);
    }
}
