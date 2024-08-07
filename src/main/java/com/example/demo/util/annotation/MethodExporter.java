package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target({ElementType.METHOD}) //作用方法
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface MethodExporter {

}
