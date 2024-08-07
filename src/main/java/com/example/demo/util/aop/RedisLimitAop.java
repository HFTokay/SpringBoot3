package com.example.demo.util.aop;

import com.example.demo.util.annotation.RedisLimitRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther zzyy
 * @create 2024-05-23 15:45
 */
@Aspect
@Component
public class RedisLimitAop
{
    Object result = null;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    private DefaultRedisScript<Long> redisLuaScript;
    @PostConstruct
    public void init()
    {
        redisLuaScript = new DefaultRedisScript<>();
        redisLuaScript.setResultType(Long.class);
        redisLuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }

    @Around("@annotation(com.example.demo.util.annotation.RedisLimitRequest)")
    public Object around(ProceedingJoinPoint joinPoint)
    {
        System.out.println("------@Around 环绕通知AAA  methodExporter -----");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //拿到RedisLimitAnnotation注解，如果存在则说明需要限流，容器捞鱼思想
        RedisLimitRequest redisLimitAnnotation = method.getAnnotation(RedisLimitRequest.class);

        if (redisLimitAnnotation != null)
        {
            //获取redis的key
            String key = redisLimitAnnotation.key();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();

            String limitKey = key + "\t" + className + "\t" + methodName;
            System.out.println("=============limitKey:" + limitKey);


            if (null == key)
            {
                throw new RuntimeException("it's danger,limitKey cannot be null") ;
            }

            long limit = redisLimitAnnotation.permitsPerSecond();
            long expire = redisLimitAnnotation.expireTime();
            List<String> keys = new ArrayList<>();
            keys.add(key);

            Long count = stringRedisTemplate.execute(
                    redisLuaScript,
                    keys,
                    String.valueOf(limit),
                    String.valueOf(expire));

            System.out.println("Access try execute is " + count + "\t" + "key" + "\t" + key);
            if (count != null && count == 0)
            {
                System.out.println("启动限流功能key: "+key);
                // 第一学历统招本科，愿意跟着阳哥学技术，需要内推或高阶java资料学习的同学发邮件zzyybs@126.com
                //throw new RedisLimitException(redisLimitAnnotation.msg());
                return redisLimitAnnotation.smg();
            }
        }


        try {
            result = joinPoint.proceed();//放行
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        System.out.println("------@Around 环绕通知BBB  methodExporter -----");
        System.out.println();
        System.out.println();

        return result;
    }
















    /**
     * 构建redis lua脚本,防御性编程，程序员自我保护，闲聊
     * 能用LuaScript，就不要用java拼装
     * @return
     */
    /*private String buildLuaScript() {
        StringBuilder luaString = new StringBuilder();
        luaString.append("local key = KEYS[1]");
        //获取ARGV内参数Limit
        luaString.append("\nlocal limit = tonumber(ARGV[1])");
        //获取key的次数
        luaString.append("\nlocal curentLimit = tonumber(redis.call('get', key) or '0')");
        luaString.append("\nif curentLimit + 1 > limit then");
        luaString.append("\nreturn 0");
        luaString.append("\nelse");
        //自增长 1
        luaString.append("\n redis.call('INCRBY', key, 1)");
        //设置过期时间
        luaString.append("\nredis.call('EXPIRE', key, ARGV[2])");
        luaString.append("\nend");
        return luaString.toString();
    }*/
}

