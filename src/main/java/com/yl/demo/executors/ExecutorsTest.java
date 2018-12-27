package com.yl.demo.executors;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

	public static void main(String[] args)
	     {
	         LinkedBlockingQueue<Runnable> queue =
	             new LinkedBlockingQueue<Runnable>(5);
	         //饱和策略
	         RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
	         ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue,handler);
//	         ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue);
	         for (int i = 0; i < 16 ; i++)
	         {
	             threadPool.execute(
	                 new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
	             System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
	             if (queue.size() > 0)
	             {
	                 System.out.println("----------------队列中阻塞的线程数" + queue.size());
	             }
	         }
	         threadPool.shutdown();
	     }
}
