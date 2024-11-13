package org.aaron.app.hoper.threadPool;

import java.util.concurrent.*;

public class MyThreadPool {

    public static Integer testPool(int i) {
        System.out.print("这里开始打印\n");
        return i;
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer>[] completableFutures = new CompletableFuture[5];

        for (int i = 0; i < completableFutures.length; i++) {
            int count = i;
            completableFutures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return testPool(count);
            }, executorService);
        }


        CompletableFuture.allOf(completableFutures).get(11, TimeUnit.SECONDS);

        for (CompletableFuture<Integer> completableFuture : completableFutures) {
            Integer result = completableFuture.getNow(null);
            System.out.print(result + "\n");
        }
        executorService.shutdown();
        executorService.awaitTermination(12, TimeUnit.SECONDS);
        executorService.shutdownNow();
        System.out.print("线程结束\n");
    }
}
