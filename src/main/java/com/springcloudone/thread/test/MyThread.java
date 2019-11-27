package com.springcloudone.thread.test;

/**
 *
 * @author xw
 * @date 2019/11/20 10:20
 */
public class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService){
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.addNum();
    }
}
