package com.envcloud.redisson.service;

import com.envcloud.redisson.model.User;

public interface UserService {

    Integer addUser(User user);

    Integer updateUser(User u);

    Integer deleteUser(Integer userId);

    User getUser(Integer userId);
}
