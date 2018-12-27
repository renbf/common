package com.yl.demo.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Threadpools {
	/**
     * 我们获取四次次线程，观察4个线程地址
     * @param args
     */
    public static  void main(String[]args)
    {
//    	newCachedThreadPool();
//    	newFixedThreadPool();
    	newScheduledThreadPool();
//    	newSingleThreadExecutor();
        
    }
    /**
     * 可以有无限大的线程数进来（线程地址不一样）
     */
    public static void newCachedThreadPool() {
    	ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("****************************newCachedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
          newCachedThreadPool.execute(new ThreadForpools(index));
        }
    }
    /**
     * 每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）
     */
    public static void newFixedThreadPool() {
    	//线程池允许同时存在两个线程
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newFixedThreadPool.execute(new ThreadForpools(index));
        }
    }
    /**
     * 延迟三秒之后执行，除了延迟执行之外和newFixedThreadPool基本相同，可以用来执行定时任务
     */
    public static void newScheduledThreadPool() {
    	ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            //延迟三秒执行
            newScheduledThreadPool.schedule(new ThreadForpools(index),3, TimeUnit.SECONDS);
        }
    }
    /**
     * 只存在一个线程，顺序执行
     */
    public static void newSingleThreadExecutor() {
    	ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("****************************newFixedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newSingleThreadExecutor.execute(new ThreadForpools(index));
        }

    }
}
