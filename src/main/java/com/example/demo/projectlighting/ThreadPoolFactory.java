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
                /**
                 //线程池的拒接策略 RejectedExecutionHandler接口
                 //四大拒绝策略：
                 AbortPolicy（默认策略）‌
                 ‌行为‌：直接抛出RejectedExecutionException异常，中断任务提交流程。
                 ‌适用场景‌：需严格保证任务不丢失的场景，但需上层逻辑处理异常‌

                 CallerRunsPolicy
                 ‌行为‌：由提交任务的线程（如主线程）直接执行被拒绝的任务。
                 ‌优点‌：避免任务丢失，但可能阻塞提交线程‌

                 DiscardPolicy
                 ‌行为‌：静默丢弃被拒绝的任务，无任何通知或异常。
                 ‌风险‌：可能导致数据丢失，适用于允许任务丢弃的非关键场景‌
                 DiscardOldestPolicy
                 ‌行为‌：丢弃队列中存活时间最长的任务（队列头部任务），然后重试提交新任务。
                 ‌适用场景‌：可容忍部分旧任务丢失，但需保证新任务优先处理‌

                 */

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
