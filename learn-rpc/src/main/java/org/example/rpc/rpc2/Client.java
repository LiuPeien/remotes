package org.example.rpc.rpc2;

import org.example.rpc.common.User;

public class Client {
    public static void main(String[] args) throws Exception {
        Stub stub = new Stub();
        User user = stub.findUserById(123);
        System.out.println(user);
    }
}
