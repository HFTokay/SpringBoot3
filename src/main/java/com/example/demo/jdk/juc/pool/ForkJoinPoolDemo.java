package com.example.demo.jdk.juc.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 分叉合并池   把大任务拆分成小任务  给多个线程执行  最后合并执行结果
		// ForkJoinPool

//		long sum = 0;
//		for (long i = 0; i < 100000000000L; i++) {
//			sum += i;
//		}
//		System.out.println(sum);
		
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		ForkJoinTask<Long> submit = forkJoinPool.submit(new Sum(1, 100000000000L));
		System.out.println(submit.get());
		forkJoinPool.shutdown();//关闭
	
	}
}

class Sum extends RecursiveTask<Long>{
	private long start;
	private long end;
	public Sum(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}
	@Override
	protected Long compute() {
		// TODO Auto-generated method stub
		if (end - start <= 10000) {
			long sum = 0;
			for (long i = start; i < end; i++) {
				sum += i;
			}
			return sum;
		} else {
			long mid = (start + end) / 2;
			Sum left = new Sum(start, mid);
			Sum right = new Sum(mid + 1, end);
			left.fork();
			right.fork();
			return left.join() + right.join();
		}
	}
}


