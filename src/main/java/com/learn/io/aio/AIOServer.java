package com.learn.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {
    private final int port;

    public AIOServer(int port) {
        this.port = port;
        listen();
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup =
                    AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            final AsynchronousServerSocketChannel server =
                    AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("Server started, port:" + port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                Charset utf = Charset.forName("utf-8");

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("Start to get data");
                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        System.out.println(utf.decode(buffer).toString());
                        result.write(buffer);
                        buffer.flip();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    System.out.println("Operation finished");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("I/O operation filed: " + exc);
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new AIOServer(port);
    }
}
