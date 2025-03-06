package com.example.demo.projectlighting;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolFactory {

    public static ThreadPoolExecutor createGatewayPool(String gatewayId) {
        return new ThreadPoolExecutor(
                5, 10, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadFactory() {
                    private AtomicInteger count = new AtomicInteger(0);
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "GatewayPool-" + gatewayId + "-" + count.incrementAndGet());
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
