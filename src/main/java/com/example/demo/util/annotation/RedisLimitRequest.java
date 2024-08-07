package com.example.demo.util.annotation;



import java.lang.annotation.*;

@Target({ElementType.METHOD}) //作用方法
@Retention(RetentionPolicy.RUNTIME)//运行时生效
@Documented
public @interface RedisLimitRequest {

    String key() default "";

    //过期时间内 最多访问次数
    long permitsPerSecond() default 3;

    //过期时间  默认秒
    long expireTime() default 30;

    String smg() default "系统繁忙，稍后再试";
}
