package com.example.mylibrarydemo.service;

import com.example.mylibrarydemo.dao.UserMapper;
import com.example.mylibrarydemo.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService{
    @Autowired
    public UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

    @Override
    public User checkManager(User user) {
        return userMapper.checkManager(user);
    }

}
