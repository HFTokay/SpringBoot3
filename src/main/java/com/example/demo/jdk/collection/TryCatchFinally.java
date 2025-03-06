package com.example.demo.jdk.collection;

/**
 * try块：用于捕获异常。其后可接零个或多个 catch 块，
 * 如果没有 catch 块，则必须跟一个 finally 块。
 * catch块：用于处理 try 捕获到的异常。
 * finally 块：
 * 无论是否捕获或处理异常，finally 块里的语句都会被执行。
 * 当在 try 块或 catch 块中遇到 return 语句时，finally 语句块将在方法返回之前被执行。
 * ------
 * 著作权归JavaGuide(javaguide.cn)所有
 * 基于MIT协议
 * 原文链接：https://javaguide.cn/java/basis/java-basic-questions-03.html
 */


public class TryCatchFinally {

    public static void main(String[] args) {
        System.out.println(f(2));

        try {
            System.out.println("Try to do something");
            return ;
            //throw new RuntimeException("RuntimeException");
            // int b = 1/0; //by zero
        } catch (Exception e) {
            System.out.println("Catch Exception -> " + e.getMessage());
        } finally {
            System.out.println("Finally");
        }
    }


    public static int f(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }

}
