package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;


public interface UserService {

    public User getUserById(Long id);

    public List<User> getUserList();

    public User getUserByIdByRedisCache(Long id);

    public Integer insertUser(User user);

}
