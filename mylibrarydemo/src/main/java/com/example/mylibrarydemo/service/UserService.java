package com.example.mylibrarydemo.service;

import com.example.mylibrarydemo.entry.User;

public interface UserService {
    User checkUser(User user);

    User checkManager(User user);
}
