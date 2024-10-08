package com.elifeindia.crm.printersdk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator
 *
 * @author 猿史森林
 *         Date: 2017/11/2
 *         Class description:
 */
public class ThreadPool {

    private static com.elifeindia.crm.printersdk.ThreadPool threadPool;
    /**
     * java线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 最大线程数
     */
    private final static int MAX_POOL_COUNTS = 20;

    /**
     * 线程存活时间
     */
    private final static long ALIVETIME = 200L;

    /**
     * 核心线程数
     */
    private final static int CORE_POOL_SIZE = 20;

    /**
     * 线程池缓存队列
     */
    private BlockingQueue<Runnable> mWorkQueue = new ArrayBlockingQueue<>(CORE_POOL_SIZE);

    private ThreadFactory threadFactory = new ThreadFactoryBuilder("ThreadPool");

    private ThreadPool() {
        //初始化线程池 核心线程数为20，最大线程数30，线程存活200L，线程队列mWorkQueue,
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_COUNTS, ALIVETIME, TimeUnit.SECONDS, mWorkQueue, threadFactory);
    }

    public static com.elifeindia.crm.printersdk.ThreadPool getInstantiation() {
        if (threadPool == null) {
            threadPool = new com.elifeindia.crm.printersdk.ThreadPool();
        }
        return threadPool;
    }
    //为线程池添加任务
    public void addTask(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("addTask(Runnable runnable)传入参数为空");
        }
        if (threadPoolExecutor != null && threadPoolExecutor.getActiveCount() < MAX_POOL_COUNTS) {
            threadPoolExecutor.execute(runnable);
        }
    }

    public void stopThreadPool() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
            threadPool = null;
        }
    }
}
