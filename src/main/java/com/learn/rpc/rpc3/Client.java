package com.learn.rpc.rpc3;

import com.learn.rpc.common.IUserService;
import com.learn.rpc.common.User;

public class Client {
    public static void main(String[] args) {
        IUserService service = Stub.getStub();
        User user = service.findUserById(123);
        System.out.println(user);
    }
}
