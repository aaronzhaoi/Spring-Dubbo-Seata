package org.aaron.app.hoper.locker;

import java.util.concurrent.Semaphore;

public class Samphone {

    public static void testSaxophone() throws InterruptedException {
        SamTask task = new SamTask();
        for (int i = 0; i < 18; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

    }

    public static void main(String[] arg) throws InterruptedException {
        Samphone.testSaxophone();
    }
}

class SamTask implements Runnable {

    public Semaphore semaphore = new Semaphore(4);

    @Override
    public void run() {
        try {
            while (!semaphore.tryAcquire()) {
            }
            System.out.print(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphore.release();
        }

    }
}
