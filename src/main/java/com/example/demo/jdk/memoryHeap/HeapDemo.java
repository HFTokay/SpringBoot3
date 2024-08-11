package com.example.demo.jdk.memoryHeap;

import java.util.ArrayList;
import java.util.List;

public class HeapDemo {

    public static void main(String[] args) {
        heapOOM();
    }


    //Run >Edit Configurations 设置堆内存大小的参数   -Xms:1k --Xmx:2K
    public static void heapOOM(){
        List<String> list = new ArrayList<>();
        while(true){
            list.add("str");

        }
    }

}
