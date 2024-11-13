package org.aaron.app.hoper.threaThread;

public class ThreadSequenceUsingWaitNotify {
    private static final Object lock = new Object();
    private static int turn = 1;


    public static void main(String[] args) {
        /**
         * 根据task的构造值判断，只有和构造值相同时才让线程运行
         */
        Thread t1 = new Thread(new Task(1));
        Thread t2 = new Thread(new Task(2));
        Thread t3 = new Thread(new Task(3));

        t1.start();
        t2.start();
        t3.start();
    }


    static class Task implements Runnable {
        private int threadId;

        public Task(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (turn != threadId) {
                        lock.wait();
                    }
                    System.out.println("Thread " + threadId);
                    turn++;
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
