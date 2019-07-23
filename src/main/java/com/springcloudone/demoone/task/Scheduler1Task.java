package com.springcloudone.demoone.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xw
 * @date 2019/7/15 16:23
 */
@Component
public class Scheduler1Task {
    private int count=0;

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
//        System.out.println("这个定时器已经运行：  "+(count++));
    }
}
