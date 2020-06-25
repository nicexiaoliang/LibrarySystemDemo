package com.example.mylibrarydemo.dao;

import com.example.mylibrarydemo.entry.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccountMapper {
    public List<Account> findAll();
}
