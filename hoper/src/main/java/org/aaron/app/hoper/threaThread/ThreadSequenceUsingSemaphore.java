package org.aaron.app.hoper.threaThread;

import java.util.concurrent.Semaphore;

public class ThreadSequenceUsingSemaphore {
    /**
     * 利用信号量每次释放时会在可用的信号量上面+1的原理
     */
    private static final Semaphore semaphore1 = new Semaphore(1);
    private static final Semaphore semaphore2 = new Semaphore(0);
    private static final Semaphore semaphore3 = new Semaphore(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                semaphore1.acquire();
                System.out.println("Thread 1");
                semaphore2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                semaphore2.acquire();
                System.out.println("Thread 2");
                semaphore3.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                semaphore3.acquire();
                System.out.println("Thread 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        semaphore2.release();
        semaphore2.release();

        t1.start();
        t2.start();
        t3.start();
    }
}
