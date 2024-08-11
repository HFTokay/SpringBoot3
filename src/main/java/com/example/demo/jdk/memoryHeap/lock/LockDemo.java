package com.example.demo.jdk.memoryHeap.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	static int sumSync = 0;
	
	static int sumLock = 0;

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock reentrantLock = new ReentrantLock();
		
		new Thread(new AddSync()).start();
		new Thread(new AddSync()).start();
		Thread.sleep(2000);
		System.out.println("sumSync:"+sumSync);
		
		
		new Thread(new AddLock(reentrantLock)).start();
		new Thread(new AddLock(reentrantLock)).start();
		Thread.sleep(2000);
		System.out.println("sumLock:"+sumLock);
		

	}

}

class AddSync implements Runnable {

	@Override
	public void run() {
		//synchronized (AddSync.class) {
			for (int i = 0; i < 1000; i++) {
				LockDemo.sumSync++;
			}
		//}
	}
}


class AddLock implements Runnable {
	private Lock lock;
	
	public AddLock(Lock lock) {
		super();
		this.lock = lock;
	}



	@Override
	public void run() {
		lock.lock();
		for (int i = 0; i < 1000; i++) {
			LockDemo.sumLock++;
		}
		lock.unlock();
	}
}
