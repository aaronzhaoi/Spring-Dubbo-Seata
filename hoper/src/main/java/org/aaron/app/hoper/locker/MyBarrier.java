package org.aaron.app.hoper.locker;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

public class MyBarrier {
    /**
     * 通过栅栏设置为1，可以昂线程按序运行,用于程序分组同时进行，单个时用于顺序执行
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

    public void testCycBarrier() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(3000);
            new Thread(new TaskBarrier(i, cyclicBarrier)).start();
        }
    }


    public static void main(String[] arg) throws InterruptedException {
        MyBarrier myBarrier = new MyBarrier();
        myBarrier.testCycBarrier();
    }
}

class TaskBarrier implements Runnable {

    private int i;
    private CyclicBarrier cyclicBarrier;

    public TaskBarrier(int i, CyclicBarrier cyclicBarrier) {
        this.i = i;
        this.cyclicBarrier = cyclicBarrier;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.print(System.currentTimeMillis() + " Task:" + i + " 完成任务\n");
        cyclicBarrier.await();
    }
}
