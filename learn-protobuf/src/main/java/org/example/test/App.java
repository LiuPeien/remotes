package org.example.test;

import com.google.protobuf.InvalidProtocolBufferException;
import org.example.proto.Messaage;

public class App {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Messaage.Message.Builder builder = Messaage.Message.newBuilder();
        builder.setId(1);
        builder.setName("jinhongliu");

        Messaage.Message message = builder.build();
        System.out.println(message);

        byte[] bytes = message.toByteArray();
        Messaage.Message message1 = Messaage.Message.parseFrom(bytes);
        System.out.println(message1);
    }
}
