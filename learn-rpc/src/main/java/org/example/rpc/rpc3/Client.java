package org.example.rpc.rpc3;

import org.example.rpc.common.IUserService;
import org.example.rpc.common.User;

public class Client {
    public static void main(String[] args) {
        IUserService service = Stub.getStub();
        User user = service.findUserById(123);
        System.out.println(user);
    }
}
