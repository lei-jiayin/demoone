package com.springcloudone.demoone.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xw
 * @date 2019/7/15 16:24
 */
@Component
public class Scheduler2Task {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 延迟六秒执行
     */
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
//        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
