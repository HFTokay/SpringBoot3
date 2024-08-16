package com.example.demo.config;

import com.example.demo.controller.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册自定义的拦截器
        DemoInterceptor demoInterceptor = new DemoInterceptor();

//        执行顺序
//        HandlerInterceptor:--------preHandle()
//        HandlerInterceptor:--------postHandle()
//        HandlerInterceptor:--------afterCompletion()

        // 执行/home/page  这个接口拦截
        registry.addInterceptor(demoInterceptor).addPathPatterns("/home/page");
    }
}
