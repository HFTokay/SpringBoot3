package com.example.demo.jdk.juc.pool;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExectorServiceDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		//可以用来做定时任务
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

		/**
		 * 线程池 提交 线程的两种方式 1.submit方法 2.schedule方法
		 */

		// Future<String> submit = (Future<String>) ses.submit(new ScheduledCallable());
		// System.out.println(submit.get());//抛出异常

		// command 提交的任务
		// delay 延时时间
		// unit 时间单位
		// ses.schedule(new ScheduledRunable(),2,TimeUnit.SECONDS);

		// command
		// initialDelay
		// period
		// unit
		// 每隔5秒钟执行一次 从上一次开始来计算下一次的启动时间
		// 如果线程执行时间大于间隔时间，则两次任务的间隔时间以线程执行时间为准
		ses.scheduleAtFixedRate(new ScheduledRunable(), 0, 5, TimeUnit.SECONDS);
		
		
		// 每隔5秒钟执行一次 从上一次结束来计算下一次的启动时间
		ses.scheduleWithFixedDelay(new ScheduledRunable(), 0, 5, TimeUnit.SECONDS);
		
		ses.shutdown();//启动有序关闭，其中之前提交的任务将被执行，
		               //但不会接受新任务。如果已关闭，则调用不会产生额外影响
		
		
		List<Runnable> shutdownNow = ses.shutdownNow();
		             //尝试停止所有正在执行的任务，
		             //停止等待中的任务的处理，并返回等待执行的任务列表。
		

	}

}

class ScheduledRunable implements Runnable {
	@Override
	public void run() {
		System.out.println("----running----");
		try {
			Thread.sleep(8000);// 模拟业务耗时  2秒 8秒
			System.out.println("----finished----");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ScheduledCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("----calling-----");
		return "SUCESS-Callable";
	}
}
