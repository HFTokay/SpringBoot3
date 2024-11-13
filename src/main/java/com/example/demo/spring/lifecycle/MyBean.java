package com.example.demo.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring声明周期
 */
@Component("myBean")
public class MyBean implements InitializingBean,
        BeanFactoryAware, BeanNameAware, ApplicationContextAware,
        BeanPostProcessor,
        DisposableBean

{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("======================"+"setName:"+name);
        this.name = name;
    }

    @Autowired
    public void autowired(@Value("${JAVA_HOME}") String home){
        System.out.println("调用了依赖注入 MyBean autowired(String home):" + home);
    }

    public MyBean() {
        System.out.println("1.调用无参构造方法：MyBean()");
    }

    public MyBean(String name) {
        this.name = name;
        System.out.println("1.调用有参参构造方法：MyBean(String name)");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用了BeanFactoryAware  >>   setBeanFactory方法");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("调用了BeanNameAware  >>   setBeanName（）方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("调用了ApplicationContextAware  >>   setApplicationContext（）方法");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用了DisposableBean  >>   destroy（）方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用了InitializingBean  >>   afterPropertiesSet()方法");

    }


}
