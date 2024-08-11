package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.annotation.MethodExporter;
import com.example.demo.util.annotation.RedisLimitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

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

}
