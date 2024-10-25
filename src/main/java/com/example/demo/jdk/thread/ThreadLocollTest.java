package com.example.demo.jdk.thread;


public class ThreadLocollTest {

/**
    get()：获取当前线程所对应的线程本地变量的值。
    set(T value)：设置当前线程的线程本地变量的值。
    remove()：移除当前线程的线程本地变量的值。
    initialValue()：返回当前线程本地变量的初始值，这个方法是一个钩子方法，子类可以通过重写该方法来提供自定义的初始值。
*/
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> t1 = new ThreadLocal();
        Thread thread1 = new Thread(
                new Runnable() {
                    public void run() {
                        printThreadInfo("AAA");
                        t1.set("A");
                        System.out.println( "1111111111:" + t1.get() );

                    }
                }
        );

        Thread thread2 = new Thread(
                new Runnable() {
                    public void run() {
                        printThreadInfo("BBB");
                        t1.set("B");
                        System.out.println( "2222222222:" + t1.get() );


                    }
                }
        );

        thread1.start();
        thread2.start();
        //Thread.sleep(2000);
        t1.set("C");
        System.out.println( "3333333333:" + t1.get() );

    }

    public static void printThreadInfo(String name) {
        System.out.println(name + ":  " +
                Thread.currentThread().getName() + ":  " +
                Thread.currentThread().getState());


    }
}
