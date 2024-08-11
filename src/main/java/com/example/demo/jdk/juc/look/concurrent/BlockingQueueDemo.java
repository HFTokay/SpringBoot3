package com.example.demo.jdk.juc.look.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		
    BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(5);
	
    arrayBlockingQueue.add("a");//添加元素
	arrayBlockingQueue.add("b");
	arrayBlockingQueue.offer("c");//添加元素
	arrayBlockingQueue.add("d");
	arrayBlockingQueue.put("e");//添加元素  有异常 InterruptedException
	System.out.println(arrayBlockingQueue);
	
	//arrayBlockingQueue.add("f");//java.lang.IllegalStateException: Queue full
	
	
	//boolean offer = arrayBlockingQueue.offer("f");  //返回bool  
	//System.out.println(offer);
	
	
	arrayBlockingQueue.offer("f",2,TimeUnit.SECONDS);
	
	
	
	//获取队列的值
	String remove = arrayBlockingQueue.remove();
	System.out.println(remove);
	
	String poll = arrayBlockingQueue.poll();
	System.out.println(poll);
	
	String take = arrayBlockingQueue.take();
	System.out.println(take);
	
	//阻塞队列 获取值
	String pollTimeUnit = arrayBlockingQueue.poll(2,TimeUnit.SECONDS);
	System.out.println(pollTimeUnit);
	
	System.out.println(arrayBlockingQueue);
	
	//BlockingDeque<E>  //阻塞双向队列
	
		
	}

}











































































































