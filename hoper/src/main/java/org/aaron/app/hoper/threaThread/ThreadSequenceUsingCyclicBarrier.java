package org.aaron.app.hoper.threaThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadSequenceUsingCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier1 = new CyclicBarrier(2);
        CyclicBarrier barrier2 = new CyclicBarrier(2);
        /**
         * 在上一个线程运行完成以后才进入到栅栏，利用的是进入栅栏的时机
         */
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1");
                barrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                barrier1.await();
                System.out.println("Thread 2");
                barrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                barrier2.await();
                System.out.println("Thread 3");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
