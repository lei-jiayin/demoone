package com.springcloudone.thread.test;

/**
 * @author xw
 * @date 2019/11/20 10:07
 */
public class Run {
    public static void main(String[] args) {
        // 以下是原子类的 线程安全
        /*AddCountThread addCountThread = new AddCountThread();
        Thread t1 = new Thread(addCountThread);
        t1.start();
        Thread t2 = new Thread(addCountThread);
        t2.start();
        Thread t3 = new Thread(addCountThread);
        t3.start();*/

        // 以下是原子类的 线程不安全性
        try{
            MyService service = new MyService();
            MyThread[] array = new MyThread[5];
            for (int i = 0; i < array.length; i++){
                array[i] = new MyThread(service);
            }
            for (int i = 0; i < array.length; i++){
                Thread.sleep(2000);
                array[i].start();
            }
            Thread.sleep(1000);
//            System.out.println(MyService.aiRef.get());
            System.out.println(service.aiRef.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
