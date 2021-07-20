package com.learn.socket.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private int port;
    private ServerSocket server;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        System.out.println("Server start");
        try {
            server = new ServerSocket(this.port);
            Socket client = server.accept();
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "utf-8"), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String reply = sc.nextLine();
                writer.println(reply);
            }
        }).start();

        new Thread(() -> {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message from client: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new Server(8080).start();
    }
}
