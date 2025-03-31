package com.example.demo.util.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@Service
public class RedisCAPLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DistributeRedisLockClient distributeRedisLockClient;


    public void deduct() {
        DistributeRedisLock redisLock = this.distributeRedisLockClient.getRedisLock("lock");
        redisLock.lock();
        try {
            //查看库存
            String stock = redisTemplate.opsForValue().get("CAP_STOCK").toString();
            //判断库存是否够
            if (stock != null && stock.length() != 0) {
                Integer st = Integer.valueOf(stock);
                if (st > 0) {
                    //扣减库存
                    try {
                      TimeUnit.SECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    redisTemplate.opsForValue().set("CAP_STOCK", String.valueOf(st - 1));
                }

            }
        } finally {
            redisLock.unlock();
        }

    }

    public void deduct2() {

        String uuid = UUID.randomUUID().toString();

        //重试
        while (!this.redisTemplate.opsForValue().setIfAbsent("lock", "1111", 5, TimeUnit.SECONDS)) {
            try {
                Thread.sleep(50); //让锁竞争压力减少
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        try {
            //查询库存信息
            String stock = redisTemplate.opsForValue().get("CAP_STOCK").toString();
            //判断库存是否够
            if (stock != null && stock.length() != 0) {
                Integer st = Integer.valueOf(stock);
                if (st > 0) {
                    //扣减库存
                    redisTemplate.opsForValue().set("CAP_STOCK", String.valueOf(st - 1));
                }
            }
        } finally {
            //解锁  先判断自己的锁 是不是  防止锁的误删
//            if (uuid.equals(this.redisTemplate.opsForValue().get("lock").toString())) {
//                this.redisTemplate.delete("lock");
//            }


            //使用 lub 脚本
            String lubSring = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                    "then " +
                    "   return redis.call('del', KEYS[1]) " +
                    "else " +
                    "   return 0 " +
                    "end";

            this.redisTemplate.execute(new DefaultRedisScript<>(lubSring, Boolean.class), Arrays.asList("lock"), uuid);

        }
    }

}

