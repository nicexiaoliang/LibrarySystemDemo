package com.example.mylibrarydemo.test;

import com.example.mylibrarydemo.dao.AccountMapper;
import com.example.mylibrarydemo.entry.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myservice")
public class Test {
    @Autowired(required = false)
    public AccountMapper accountMapper;


    public void getall() {
        List<Account> all = this.accountMapper.findAll();
        System.out.println(all);
    }
}
