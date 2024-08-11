package com.example.demo.jdk.juc.look;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		
		new Thread(new Table(semaphore),"1号客人").start();
		new Thread(new Table(semaphore),"2号客人").start();
		new Thread(new Table(semaphore),"3号客人").start();
		new Thread(new Table(semaphore),"4号客人").start();
		new Thread(new Table(semaphore),"5号客人").start();
	}

}

class Table implements Runnable {
	private Semaphore semaphore;
	public Table(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			// 后来的线程就阻塞
			semaphore.acquire();  //获取信号   当信号不足时就阻塞
		
			System.out.println(Thread.currentThread().getName() + ":占领了一张桌子");
			Thread.sleep((long) (Math.random()*20000));//模拟用餐时间
			System.out.println(Thread.currentThread().getName() + ":吃完饭走了");
			semaphore.release();//释放信号
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	

}
