package com.example.demo.spring.proxy;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        int sum = a + b;
        System.out.println("CalculatorImpl 方法内部 sum = " + sum);
        return sum;
    }
}
