package com.example.demo.jdk.juc.look;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * 1.CountDownLatch:团锁/线程递减锁。对线程进行计数，在计数归零之前，线程会被阻塞
 * 			await()
 * 			countDown()
 * 
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(6);
		new Thread(new Teacher(countDownLatch)).start();
		new Thread(new Student(countDownLatch)).start();
		new Thread(new Student(countDownLatch)).start();
		new Thread(new Student(countDownLatch)).start();
		new Thread(new Student(countDownLatch)).start();
		new Thread(new Student(countDownLatch)).start();
		countDownLatch.await();
		// 在计数归零之前  让主函数阻塞
		System.out.println("开始考试---------");
	}
}

class Teacher implements Runnable {
	private CountDownLatch countDownLatch;
	public Teacher(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("考官到达考场");
			countDownLatch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Student implements Runnable {
	private CountDownLatch countDownLatch;
	public Student(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	@Override
	public void run() {
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("考生到达考场");
			countDownLatch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
