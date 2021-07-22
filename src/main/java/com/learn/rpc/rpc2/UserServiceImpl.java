package com.learn.rpc.rpc2;

import com.learn.rpc.common.IUserService;
import com.learn.rpc.common.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "Bob");
    }
}
