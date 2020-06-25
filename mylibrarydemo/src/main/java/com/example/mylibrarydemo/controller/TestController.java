package com.example.mylibrarydemo.controller;

import com.example.mylibrarydemo.dao.AccountMapper;
import com.example.mylibrarydemo.entry.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class TestController {
    @Autowired
    public AccountMapper accountMapper;
    @RequestMapping("/testSpringboot")
    public String sayHello() {
        return "hello,world";
    }

    @RequestMapping("/testdao")
    public List<Account> getAll() {
        return accountMapper.findAll();
    }
}
