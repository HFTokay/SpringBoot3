package com.example.demo.spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeatMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("myBean.xml");

        MyBean myBean =  context.getBean("myBean", MyBean.class);

        System.out.println(myBean);

    }
}
