package com.springcloudone.thread.task;

import java.util.Date;
import java.util.Timer;

/**
 * @author xw
 * @date 2019/11/27 9:51
 */
public class Run {
    public static void main(String[] args) {
        Timer timer = new Timer();
        MyTask myTask = new MyTask();
        timer.schedule(myTask, new Date(), 5000);
    }
}