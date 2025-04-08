package com.example.demo.util.springaop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.Annotation;

/**
 * 用于目标对象添加功能增强
 */
public class LogAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("LogAdvice:start: " + System.currentTimeMillis());
        Object result = invocation.proceed();//执行方法
        System.out.println("LogAdvice:end:" + " : " + System.currentTimeMillis());
        return result;
    }
}
