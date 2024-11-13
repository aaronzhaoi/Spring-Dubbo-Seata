package org.aaron.app.hoper.locker;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountDownLatch {
    /**
     * 用于数量控制
     */
    public static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void testCountDownLatch() throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executors.submit(new TaskCountDownLatch(i, countDownLatch));
        }
        System.out.print("主线程进入等待\n");
        Thread.sleep(5000);
        countDownLatch.await();
        System.out.print("主线程结束\n");
    }


}


class TaskCountDownLatch implements Runnable {

    private int i;
    private CountDownLatch countDownLatch;

    public TaskCountDownLatch(int i, CountDownLatch countDownLatch) {
        this.i = i;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.print("输出：" + countDownLatch.getCount() + " " + random.nextInt() + " " + Thread.currentThread().getName() + "\n");
        countDownLatch.countDown();

    }
}