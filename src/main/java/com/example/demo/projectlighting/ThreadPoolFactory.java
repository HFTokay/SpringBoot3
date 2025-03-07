package com.example.demo.projectlighting;


import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolFactory {

    private static Map<String, ThreadPoolExecutor> poolMap = new ConcurrentHashMap<>();

    public static ThreadPoolExecutor getGatewayPool(String gatewayId) {
        return poolMap.computeIfAbsent(gatewayId, id -> createGatewayPool(id));
    }


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


    public static void logPoolStatus(String gatewayId) {
        ThreadPoolExecutor executor = poolMap.get(gatewayId);
        if (executor == null) {
            System.err.println("ERROR: ThreadPool [" + gatewayId + "] not found");
            return;
        }
        System.out.printf("[%s] Active=%d, Queue=%d%n",
                gatewayId, executor.getActiveCount(), executor.getQueue().size());
        System.out.println("Pool {}: " + gatewayId);
        System.out.println("Active={}: " + executor.getActiveCount());
        System.out.println("Queue={}: " + executor.getQueue().size());
    }

}
