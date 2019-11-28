package com.springcloudone.netty.timeServer1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xw
 * @date 2019/11/28 9:47
 */
public class TimeServerHandlerExecutePool {
    private ExecutorService executorService;

    /**
     * 构造器
     * @param maxPoolSize 最大线程数
     * @param queueSize 队列数
     */
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
        executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize)
        );
    }

    /**
     * 执行器
     * @param runnable
     */
    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
