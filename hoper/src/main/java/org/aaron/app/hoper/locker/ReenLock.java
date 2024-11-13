package org.aaron.app.hoper.locker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenLock {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void testLock() {
        Task task = new Task();
        new Thread(task).start();
        new Thread(task).start();
    }

    public static void testCondition() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.print("这里开始await\n");
                try {
                    condition.await();
                    System.out.print("这里结束await\n");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(3000);
        lock.lock();
        System.out.print("这里打开Signal\n");
        condition.signal();
        lock.unlock();

    }

}

class Task implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            if (reentrantLock.tryLock()) {
                System.out.print("Get the Lock " + threadName);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.print("Fail Get the Lock " + threadName);
            }
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }
}