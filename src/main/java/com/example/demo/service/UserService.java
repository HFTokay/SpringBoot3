package com.example.demo.service;

import com.example.demo.pojo.User;


public interface UserService {

    public User getUserById(Long id);

    public User getUserByIdByRedisCache(Long id);

}
