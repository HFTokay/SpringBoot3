package com.example.demo.util.springaop;

import com.example.demo.util.springaop.annotation.RequiredLog;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class LogMethodMatcher extends StaticMethodMatcherPointcutAdvisor {

    public LogMethodMatcher() {
        setAdvice(new LogAdvice());
    }

    /**
     * 方法返回为true 为目标方法对象创建代理对象
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        RequiredLog annotation = method.getAnnotation(RequiredLog.class);
        if (annotation != null) {
            try {
                Method targetMethod = targetClass.getMethod(method.getName(), method.getParameterTypes());
                boolean annotationPresent = targetMethod.isAnnotationPresent(RequiredLog.class);
                return annotationPresent;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
