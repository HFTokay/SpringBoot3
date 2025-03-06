package com.example.demo.invterview.top;

public class Test2025_0306 {

    public static void main(String[] args) {
        Person p = new Child();
        int x=4;
        System.out.println("valueIs"+((x>4)?9.1:9));
       // System.out.println("valueIs"+((x>4)?9:99.9));
    }

}

class Person{
    private String name = "person ";
    int age = 0;
}

class Child extends Person{
    private String grade ;
}

