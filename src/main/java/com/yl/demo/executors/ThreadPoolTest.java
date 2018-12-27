package com.yl.demo.executors;

public class ThreadPoolTest implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(300);
			System.out.println(String.format("线程名称===%s", Thread.currentThread().getName()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
