package com.example.demo.jdk.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * start() 可以启动一个新线程，run() 不能
 * start() 不能被重复调用，run() 可以
 * start() 中的 run 代码可以不执行完就继续执行下面的代码，进行线程切换，而调用 run() 方法必须等待其代码全部执行完才能继续执行下面的代码
 * start() 实现了多线程，run() 没有
 * <p>
 * 原文链接：https://blog.csdn.net/RogueFist/article/details/95167192
 */


@Slf4j
public class ThreadRunStart {

    public static void main(String[] args) {

        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        printThreadInfo("AAA");
                        System.out.println("====第1种线程船创建的方法====");

                    }
                }
        );


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                printThreadInfo("runnable");
                System.out.println("====第2种线程船创建的方法====");
            }

        };

        runnable.run();



        printThreadInfo("BBB");
        thread.start();
        printThreadInfo("CCC");

    }

    public static void printThreadInfo(String name) {
        System.out.println(name + ":  " +
                Thread.currentThread().getName() + ":  " +
                Thread.currentThread().getState());


    }
}
