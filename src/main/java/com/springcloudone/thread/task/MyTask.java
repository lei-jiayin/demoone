package com.springcloudone.thread.task;

import java.util.TimerTask;

/**
 * @author xw
 * @date 2019/11/27 9:51
 */
public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("I like you!");
    }
}
