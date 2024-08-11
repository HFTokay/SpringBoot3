package com.example.demo.jdk.juc.look;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:栅栏。
 *  对线程进行计数。同样，在计数归零之前也会使线程陷入阻塞
 * 
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
		
		new Thread(new Runner(cyclicBarrier),"1号").start();
		new Thread(new Runner(cyclicBarrier),"2号").start();
		new Thread(new Runner(cyclicBarrier),"3号").start();
		new Thread(new Runner(cyclicBarrier),"4号").start();
		
	}

}


class Runner implements Runnable{
	
	private CyclicBarrier cyclicBarrier;
	
	Runner(CyclicBarrier cyclicBarrier){
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		try {
			String name = Thread.currentThread().getName();
			System.out.println(name + "-到起跑线了");
			// cyclicBarrier.reset();
			cyclicBarrier.await();
			System.out.println(name + "-跑出去了");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
