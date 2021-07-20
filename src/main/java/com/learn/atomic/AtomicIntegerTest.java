package com.learn.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void inc() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e){ //catch住中断异常，防止程序中断
            e.printStackTrace();

        }
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        AtomicIntegerTest at = new AtomicIntegerTest();
        List<Thread> ts = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    at.inc();
                }
            });
            ts.add(t);
        }
        for(Thread t: ts) {
            t.start();
        }

        for(Thread t: ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println(at.atomicInteger.get());
    }
}
