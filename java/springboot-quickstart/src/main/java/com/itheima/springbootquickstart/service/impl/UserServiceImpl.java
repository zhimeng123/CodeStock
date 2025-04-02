package com.itheima.springbootquickstart.service.impl;

import com.itheima.springbootquickstart.mapper.UserMapper;
import com.itheima.springbootquickstart.pojo.User;
import com.itheima.springbootquickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
