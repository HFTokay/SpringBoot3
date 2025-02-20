package com.example.demo.controller;

import com.example.demo.mqtt.HPFMqttClient;
import com.example.demo.mqtt.HPFMqttRecieveMessage;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.annotation.MethodExporter;
import com.example.demo.util.annotation.RedisLimitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private HPFMqttClient mqttClient;

    @Autowired
    private HPFMqttRecieveMessage mqttRecieveMessage;

    @GetMapping("/page")
    @ResponseBody
    //@MethodExporter
    @RedisLimitRequest(key = "redisLimit", permitsPerSecond = 3, expireTime = 5, smg = "接口限流 稍后再试")
    public String home() {
        UUID uuid = UUID.randomUUID();
        return "home:"+ uuid.toString();
    }

    @GetMapping("/getUserById/{id}")
    @MethodExporter
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);

    }

    @GetMapping("/TestGit")
    public String TestGit(){
        return UUID.randomUUID().toString();
    }




    //mqtt相关  发送消息
    @GetMapping("/mqttSendMessage")
    public String mqttSendMessage(){
        Map<String, Object> map = new HashMap<>();
        map.put("time", System.currentTimeMillis());
        map.put("message", "hello mqtt message:"+UUID.randomUUID().toString());
        mqttClient .publishMessage("testTopic2/1234",map.toString());
        return UUID.randomUUID().toString();
    }


    //mqtt相关  打开订阅  接收消息  实际开启一个线程，不停的接受
    @GetMapping("/mqttJSMessage")
    public String mqttJSMessage() throws InterruptedException {
        mqttRecieveMessage.recieve("testTopic2/1234");

        //一直接受
        while(true) {
            Thread.sleep(2000);
        }

    }





}
