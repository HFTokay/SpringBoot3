package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.annotation.MyRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        System.out.println("id---"+id);
        return userMapper.getUserById(id);
    }


    /**
     * 配合redis 查询先走redis  再看mysql
     * @param id
     * @return
     */
    @Override
    @MyRedisCache(keyPrefix = "user",matchValue = "#id")
    public User getUserByIdByRedisCache(Long id) {
         return userMapper.getUserById(id);
    }
}
