package com.example.demo.jdk.collection;

public class ContinueBreakReturn {
    public static void main(String[] args) {
        boolean flag = false;
        for (int i = 0; i <= 3; i++) {
            if (i == 0) {
                System.out.println("0");
            } else if (i == 1) {
                System.out.println("1");
                continue;  //指跳出当前的这一次循环，继续下一次循环。
            } else if (i == 2) {
                System.out.println("2");
                flag = true;
            } else if (i == 3) {
                System.out.println("3");
                break;//指跳出整个循环体，继续执行循环下面的语句
            } else if (i == 4) {
                System.out.println("4");
            }
            System.out.println("xixi");
        }
        if (flag) {
            System.out.println("haha");
            return; //指跳出整个循环体，继续执行循环下面的语句
        }
        System.out.println("heihei");
    }

}
