package com.example.demo.jdk.thread;


import java.util.concurrent.atomic.AtomicInteger;

public class AAA {

    public static void main(String[] args) {
        int aBreak = 100000;
        System.out.println("遍历打印次数：" + aBreak);
        long l = System.currentTimeMillis();
        AtomicInteger sum = new AtomicInteger(0);
        Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (sum.get() % 2 == 0) {
                            sum.addAndGet(1);
                            System.out.println(Thread.currentThread().getName() + "-" + sum);
                        }
                        if (sum.get() == aBreak) {
                            break;
                        }
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (sum.get() % 2 == 1) {
                            sum.addAndGet(1);
                            System.out.println(Thread.currentThread().getName() + "-" + sum);
                        }
                        if (sum.get() == aBreak) {
                            break;
                        }
                    }
                }
            }
        });
        thread1.setName("ThreadName 奇数");
        thread2.setName("ThreadName 偶数");
        thread1.start();
        thread2.start();
        while (true) {
            if (!thread1.isAlive() || !thread2.isAlive()) {
                System.out.println("耗时");
                System.out.println(System.currentTimeMillis() - l);
                break;
            }
        }
    }
}
