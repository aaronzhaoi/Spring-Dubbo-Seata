package org.aaron.app.hoper.threaThread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的await方式是让当前线程处于等待状态，直到CountDownLatch的计数器到0
 */
public class ThreadSequenceUsingCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1");
            latch1.countDown();
        });

        Thread t2 = new Thread(() -> {
            try {
                latch1.await();
                System.out.println("Thread 2");
                latch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                latch2.await();
                System.out.println("Thread 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }
}
