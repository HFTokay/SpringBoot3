package com.example.demo.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {
    private Object target;
    public Object getTarget() {
        return target;
    }
    public void setTarget(Object target) {
        this.target = target;
    }
    public ProxyFactory(Object target) {
        this.target = target;
    }
    /**
     * 动态代理  用到  Proxy
     * @return
     */
    public Object getProxyInstance() {
        //加载 动态生成代理类的类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        //目标对象的所有实现class对象的数组
        Class<?>[] interfaces = target.getClass().getInterfaces();

        //目标需要的处理器  （代理对象实现目标对象的过程 ）
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before动态代理：" + "methodName:" + method.getName() + "    args:" + Arrays.toString(args));
                Object rsult = method.invoke(target, args);
                System.out.println("after动态代理：" + "methodName:" + method.getName() + "     rsult:" + rsult);
                return rsult;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler) ;
    }
}
