package com.example.demo.jdk.thread;


public class OptimizedAAA {

    private static final int TARGET = 100000; // 总次数
    private static volatile int counter = 0;  // 使用volatile保证可见性
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("目标次数：" + TARGET);
        long start = System.currentTimeMillis();

        Thread oddThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (counter >= TARGET) break;
                    // 仅奇数线程执行递增（counter从0开始，先偶后奇）
                    if (counter % 2 == 0) {
                        counter++;
                        System.out.println(Thread.currentThread().getName() + "-" + counter); // 注释输出以提升性能
                    }
                    lock.notifyAll(); // 唤醒偶数线程
                    try {
                        if (counter < TARGET) {
                            lock.wait(); // 释放锁并等待
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "奇数线程");

        Thread evenThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (counter >= TARGET) break;
                    // 仅偶数线程执行递增
                    if (counter % 2 == 1) {
                        counter++;
                        System.out.println(Thread.currentThread().getName() + "-" + counter); // 注释输出以提升性能
                    }
                    lock.notifyAll(); // 唤醒奇数线程
                    try {
                        if (counter < TARGET) {
                            lock.wait(); // 释放锁并等待
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "偶数线程");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();

        System.out.println("总耗时: " + (System.currentTimeMillis() - start) + "ms");
    }
}
