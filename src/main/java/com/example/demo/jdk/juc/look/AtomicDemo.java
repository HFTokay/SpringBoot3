package com.example.demo.jdk.juc.look;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 原子性操作
 * 针对不同的类型，提供了对应的原子性类，
 * 实际上就是保证这个是属性在操作过程中不被其它线程抢占 
 *--- 这个效果实际上可以利用熟悉的锁来代替，所以实际开发中使用的不多
 *
 */
public class AtomicDemo {


	public static AtomicLong sum = new AtomicLong(0);;
	public static int sumInt = 0;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);

		new Thread(new Add(countDownLatch)).start();
		new Thread(new Add(countDownLatch)).start();
		
		countDownLatch.await();
		
		System.out.println(sum.get());
		
		System.out.println(sumInt);
		

	}

}

class Add implements Runnable {

	private CountDownLatch countDownLatch;
	public Add(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;

	}

	@Override
	public void run() {
//synchronized (AddSync.class) {
		for (int i = 0; i < 10000; i++) {
			AtomicDemo.sum.incrementAndGet();// 自增
			AtomicDemo.sumInt++;
		}
		countDownLatch.countDown();
//}
	}
}