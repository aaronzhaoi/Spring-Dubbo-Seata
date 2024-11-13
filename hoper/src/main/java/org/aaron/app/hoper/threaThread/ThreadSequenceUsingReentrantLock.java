package org.aaron.app.hoper.threaThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSequenceUsingReentrantLock {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static final Condition condition3 = lock.newCondition();
    private static int turn = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(1, condition1, condition2));
        Thread t2 = new Thread(new Task(2, condition2, condition3));
        Thread t3 = new Thread(new Task(3, condition3, condition1));

        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable {
        private int threadId;
        private Condition currentCondition;
        private Condition nextCondition;

        public Task(int threadId, Condition currentCondition, Condition nextCondition) {
            this.threadId = threadId;
            this.currentCondition = currentCondition;
            this.nextCondition = nextCondition;
            Thread.interrupted();
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (turn != threadId) {
                    currentCondition.await();
                }
                System.out.println("Thread " + threadId);
                turn++;
                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
