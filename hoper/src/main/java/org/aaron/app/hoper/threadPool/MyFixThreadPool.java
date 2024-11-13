package org.aaron.app.hoper.threadPool;


import java.util.concurrent.*;

public class MyFixThreadPool {
    public static void main(String[] arg) throws InterruptedException {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS,
                linkedBlockingQueue,
                new MyRejectPolicy());
        CompletableFuture<Integer>[] completableFutures = new CompletableFuture[50];
        for (int i = 0; i < completableFutures.length; i++) {
            Integer count = i;
            completableFutures[i] = CompletableFuture.supplyAsync(() -> {
                new Thread(new MyNewtask(count)).start();
                return null;
            }, executorPool);
        }
        executorPool.shutdown();// 拒绝新任务，等待队列里面的任务执行完
        while (!linkedBlockingQueue.isEmpty()) {
            System.out.print("队列不为空，开始等待" + linkedBlockingQueue.stream().count());
            executorPool.awaitTermination(1, TimeUnit.SECONDS);
        }
        executorPool.shutdown();
    }
}
