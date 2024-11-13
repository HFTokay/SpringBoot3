package com.example.demo.spring.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CjlibProxyDemo {

    static class Target{
        public void sayHello(){
            System.out.println("hello");
        }
    }
    public static void main(String[] args) {
        Target target = new Target();
        //继承  父子关系  可以强转
        Target proxy = (Target)Enhancer.create(Target.class,
                (MethodInterceptor) (obj, method, args1, proxy1) -> {
                    System.out.println("before invoke");
                    Object result = method.invoke(target, args1);
                    System.out.println("after invoke");
                    return result;
                });
        proxy.sayHello();
    }
}
