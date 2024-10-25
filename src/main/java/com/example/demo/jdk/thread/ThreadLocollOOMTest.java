package com.example.demo.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocollOOMTest {

    public static void main(String[] args) {
        ThreadLocal<User> userThreadLocal =  new ThreadLocal<User>();
        ExecutorService executorService = Executors.newFixedThreadPool(30);

        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                userThreadLocal.set(new User());
            });

            try {
                 Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(i);
        }

        executorService.shutdown();
    }
}


//Run >Edit Configurations 设置堆内存大小的参数    -Xms25m    -Xms25m
 class User{
    public Integer age = 0;
    public byte[] info = new byte[1024 * 1024];
}
