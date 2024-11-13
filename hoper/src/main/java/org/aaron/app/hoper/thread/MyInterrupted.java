package org.aaron.app.hoper.thread;

import lombok.SneakyThrows;

public class MyInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TaskInterrupted());
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
        thread.join();
        System.out.print("系统结束");
    }
}


class TaskInterrupted implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            if (Thread.currentThread().isInterrupted()) {
                System.out.print("我被Break\n");
                System.out.print("我被Break\n");
                System.out.print("我的状态" + Thread.currentThread().isInterrupted() + "\n");
                Thread.interrupted();
                System.out.print("我的状态2" + Thread.currentThread().isInterrupted() + "\n");
                break;
            } else {
                if (i < 10) {
                    System.out.print("我在运行中" + i + "\n");
                }
            }
        }
    }
}
