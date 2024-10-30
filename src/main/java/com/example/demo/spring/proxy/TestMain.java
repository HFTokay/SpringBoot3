package com.example.demo.spring.proxy;

public class TestMain {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Object proxyInstance = proxyFactory.getProxyInstance();
        Calculator calculator = (Calculator) proxyInstance;
        calculator.add(10, 20);

    }
}
