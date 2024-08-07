package com.example.demo.util.aop;

import com.example.demo.util.annotation.MethodExporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;


@Component
@Aspect
public class MethodExporterAspect {


    @Around("@annotation(com.example.demo.util.annotation.MethodExporter)")
    public Object methodExporter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object retValue = null;

        long startTime = System.currentTimeMillis();
        System.out.println("------@Around 环绕通知AAA  methodExporter -----");

        retValue = proceedingJoinPoint.proceed(); //方法放行

        long endTime = System.currentTimeMillis();
        long realtime = endTime - startTime;

        //获取到方法签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        Method method = signature.getMethod();

        MethodExporter annotation = method.getAnnotation(MethodExporter.class);
        if (annotation != null) {
            StringBuilder inputParamJson = new StringBuilder();
            Object[] args = proceedingJoinPoint.getArgs();

            DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = defaultParameterNameDiscoverer.getParameterNames(method);
            for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
                inputParamJson.append(parameterNames[i]).append("\t").append(args[i].toString()).append(";");
            }

            //返回值  json序列化
            String jsonResult = null;
            if (retValue != null) {
                jsonResult = new ObjectMapper().writeValueAsString(retValue);
            } else {
                jsonResult = "null";
            }


            System.out.println("==========aop解析Controller方法分析");
            System.out.println("请求方法名：" + proceedingJoinPoint.getTarget().getClass().getName() + "." + ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getName());
            System.out.println("请求方法参数：" + inputParamJson);
            System.out.println("返回结果：" + jsonResult);
            System.out.println("执行耗时：" + realtime + "毫秒");

            System.out.println("------@Around 环绕通知BBB  methodExporter -----");


        }

        return retValue;

    }
}
