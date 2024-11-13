package org.aaron.app.hoper.threaThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadSequenceUsingBlockingQueue {

    /**
     * 阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这两个附加的操作是：在队列为空时，获取元素的线程会等待队列变为非空。当队列满时，存储元素的线程会等待队列可用。阻塞队列常用于生产者和消费者的场景，生产者是往队列里添加元素的线程，消费者是从队列里拿元素的线程。阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素。
     */
    private static final BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
    private static final BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1");
                queue1.put(1); // Signal t2 to run
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                queue1.take(); // Wait for signal from t1
                System.out.println("Thread 2");
                queue2.put(1); // Signal t3 to run
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                queue2.take(); // Wait for signal from t2
                System.out.println("Thread 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
