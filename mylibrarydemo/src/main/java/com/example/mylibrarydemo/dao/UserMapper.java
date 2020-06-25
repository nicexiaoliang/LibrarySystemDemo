package com.example.mylibrarydemo.dao;

import com.example.mylibrarydemo.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    public User checkUser(@Param("user") User user);

    public User checkManager(@Param("user") User user);
}
