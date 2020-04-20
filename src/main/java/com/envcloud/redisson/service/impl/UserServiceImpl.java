package com.envcloud.redisson.service.impl;

import com.envcloud.redisson.model.User;
import com.envcloud.redisson.repository.UserMapper;
import com.envcloud.redisson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
//        return userMapper.addUser(user);
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
