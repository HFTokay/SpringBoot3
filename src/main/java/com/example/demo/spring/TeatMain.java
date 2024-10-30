package com.example.demo.spring;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeatMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("myBean.xml");

        MyBean myBean =  context.getBean("myBean", MyBean.class);

        System.out.println(myBean);

    }
}
