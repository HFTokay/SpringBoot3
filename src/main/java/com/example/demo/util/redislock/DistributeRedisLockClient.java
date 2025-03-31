package com.example.demo.util.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class DistributeRedisLockClient {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String uuid;

    public DistributeRedisLockClient() {
        this.uuid = UUID.randomUUID().toString();
    }

    public DistributeRedisLock getRedisLock(String lockName) {
        return new DistributeRedisLock(redisTemplate,lockName,uuid);
    }


}
