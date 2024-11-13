package org.aaron.app.hoper.threaThread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSequenceUsingAtomicInteger {
    /***
     * 利用原子类的线程安全性，只能由自己+1
     */
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (counter.get() != 1) {}
            System.out.println("Thread 1");
            counter.incrementAndGet();
        });

        Thread t2 = new Thread(() -> {
            while (counter.get() != 2) {}
            System.out.println("Thread 2");
            counter.incrementAndGet();
        });

        Thread t3 = new Thread(() -> {
            while (counter.get() != 3) {}
            System.out.println("Thread 3");
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
