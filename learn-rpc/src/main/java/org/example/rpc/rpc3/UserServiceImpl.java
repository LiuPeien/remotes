package org.example.rpc.rpc3;

import org.example.rpc.common.IUserService;
import org.example.rpc.common.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "Bob");
    }
}
