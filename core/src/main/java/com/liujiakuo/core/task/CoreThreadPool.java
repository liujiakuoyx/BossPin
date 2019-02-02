package com.liujiakuo.core.task;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 佳阔 on 2019/2/2.
 */

public class CoreThreadPool {
    private static final int CORE_POOL_SIZE = 2;//核心线程数
    private static final int MAXIMUM_POOL_SIZE = Integer.MAX_VALUE;//最大线程数
    private static final long KEEP_ALIVE_TIME = 30;//非核心线程存活时长
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static ThreadPoolExecutor mThreadPoolExecutor;

    //获取线程池
    public static synchronized ExecutorService executorService() {
        initThreadPool();
        return mThreadPoolExecutor;
    }

    public static synchronized void enqueue(Runnable r) {
        if (r == null) {
            return;
        }
        initThreadPool();
        mThreadPoolExecutor.execute(r);
    }

    public static synchronized void initThreadPool() {
        if (mThreadPoolExecutor == null) {
            mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                    MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TIME_UNIT,
                    new SynchronousQueue<Runnable>());
        }
    }
}
