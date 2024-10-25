package com.example.demo;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@SpringBootTest
public class MqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void test1() {
        String queueName = "simple.queue" ;
        String message = "message" ;
        rabbitTemplate.convertAndSend(queueName,message);
    }
}


/**
 * mq 问题  消息重复消费问题
 *
 * 简单队列
 * 工作队列
 * Fanout交换机  广播   每个消息都能收到
 * Direct交换机  定向   bindingKey + RoutingKey 指定
 * Topic 交换机  话题   通配符  *指一个  #0或多个 china.*    #.news
 * 声明队列交换机  1.代码FanoutConfig配置  队列  交换机  绑定3个ben  2.注解方式
 * mq消息序列化  可自定义  代码FanoutConfig配置 配置bean
 *
 */

@Component
class SpringRabbitListener{

    //简单队列
    @RabbitListener(queues = "simple.queue")
    public void  listerSimpleQueue(String message){
        System.out.println(message);
    }

    //work Queues  多个消费者  轮询消费direct
    @RabbitListener(queues = "work.queue")
    public void  listerWorkQueue(String message){
        System.out.println(message);
    }

    //Fanout交换机
    //Direct交换机
    //Topic交换机


    //基于注解声明队列 交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1",declare = "true"),
            exchange = @Exchange(name = "hpf.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void  listerDirectQueue(String message){
        System.out.println(message);
    }


    //消息转化器





}
