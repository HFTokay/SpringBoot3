package com.example.demo.jdk.memoryHeap.lock;

import java.util.concurrent.Exchanger;

/**
 * 交换机    交换线程之间的信息
 *
 */
public class ExchangerDemo {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		new Thread(new Producer(exchanger)).start();
		new Thread(new Consumer(exchanger)).start();
	}

}


class Producer implements Runnable {
	private Exchanger<String> exchanger;
	public Producer(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			String info = "商品";
			String exchange = exchanger.exchange(info);
		    System.out.println("商家收到了客户--:"+exchange);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Consumer implements Runnable {
	private Exchanger<String> exchanger;
	public Consumer(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			String info = "钱";
			 String msg = exchanger.exchange(info);
			 System.out.println("客户收到了商家的：" + msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
