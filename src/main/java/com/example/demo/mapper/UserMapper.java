package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    public User getUserById(Long id);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUserById(Long id);

    public List<User> getUserList();




}
