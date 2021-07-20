package com.learn.socket.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        try {
            this.socket = new Socket("localhost", port);
            System.out.println("Connect to server, start to send message");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                writer.println(message);
            }
        }).start();

        new Thread(() -> {
            try {
                String reply;
                while ((reply = reader.readLine()) != null) {
                    System.out.println("Reply from server: " + reply);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new Client(8080).start();
    }
}
