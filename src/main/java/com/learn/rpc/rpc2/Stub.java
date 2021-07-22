package com.learn.rpc.rpc2;

import com.learn.rpc.common.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Stub {
    public User findUserById(Integer id) throws Exception {
        Socket s = new Socket("localhost", 8888);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);

        s.getOutputStream().write(baos.toByteArray());
        s.getOutputStream().flush();

        DataInputStream dis = new DataInputStream(s.getInputStream());

        int receivedId = dis.readInt();
        String name = dis.readUTF();
        User user = new User(receivedId, name);

        dos.close();
        s.close();
        return user;
    }
}
