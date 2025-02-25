package com.example.demo.jdk.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyArrayList {



    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add(1,"C");
        System.out.println(list);

        list.remove("B");
        System.out.println(list);



        // 源码分析

        //1.初始化容量
        //2.扩容方式  动态数组
        //3.add方法
        //4.





    }
}
