package com.example.demo.util.redislock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;


import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributeRedisLock implements Lock {

    private StringRedisTemplate redisTemplate;
    private String lockName;
    private String uuid;
    private Long expireTime = 30L;

    public DistributeRedisLock(StringRedisTemplate redisTemplate, String lockName,String uuid) {
        this.lockName = lockName;
        this.redisTemplate = redisTemplate;
        this.uuid = uuid + ":" + Thread.currentThread().getId();
    }

    @Override
    public void lock() {
        this.tryLock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            return this.tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 加锁方法
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {

        //设置过期时间
        if (time != -1) {
            this.expireTime = unit.toSeconds(time);
        }

        String scrip = "if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1" +
                " then " +
                "   redis.call('hincrby',KEYS[1],ARGV[1], 1) " +
                "   redis.call('expire',KEYS[1],ARGV[2]) " +
                "   return 1" +
                "  else " +
                "   return 0 " +
                "end";

        //重试
        while (!this.redisTemplate.execute(new DefaultRedisScript<>(scrip, Boolean.class), Arrays.asList(lockName), uuid, String.valueOf(expireTime))) {
            Thread.sleep(50);
        }

        //加锁 续期
        this.renewExpire();

        return true;
    }

    /**
     * 解锁方法
     */
    @Override
    public void unlock() {
        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 0 " +
                "then " +
                "   return nil " +
                "elseif redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 " +
                "then " +
                "   return redis.call('del',KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";


        Long execute = this.redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName), uuid);
        if (execute ==null) {
            throw  new IllegalMonitorStateException("this lock does not belong to you ");
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }


    private void renewExpire(){

        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " +
                "   return redis.call('expire', KEYS[1], ARGV[2]) " +
                "else " +
                "   return 0 " +
                "end";

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Boolean execute = redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuid, String.valueOf(expireTime));
                if (execute) {
                    renewExpire();
                }
            }
        },this.expireTime * 1000 /3);


    }
}
