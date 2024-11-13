package org.aaron.app.hoper.threaThread;

import java.util.concurrent.CompletableFuture;

/**
 * thenRun 和 thenRunAsync 两者都用于在 CompletableFuture 完成后执行 Runnable 任务。但是，它们有一些显著的差异：
 *
 * 同步与异步执行：
 *
 * thenRun：任务同步执行并在调用线程上运行。
 * thenRunAsync：任务异步执行，可能在不同的线程上运行，具体取决于CompletableFuture的执行策略。
 * 线程执行上下文：
 *
 * thenRun：任务在当前线程上运行，因此如果在主线程上调用thenRun，任务也会在主线程上运行。
 * thenRunAsync：任务通常在 ForkJoinPool.commonPool() 中的工作线程上异步运行。这可以增强并发性，但需要注意的是，如果在主线程上调用 thenRunAsync，任务可能会在后台线程上执行，因此需要谨慎考虑线程安全。
 * 返回值：
 *
 * thenRun 不返回任何值，仅用于任务执行；它不会产生任何结果。
 * thenRunAsync 也不返回任何值，用于异步任务执行而不产生结果
 */

public class ThreadSequenceUsingCompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("Thread 1");
        });

        CompletableFuture<Void> future2 = future1.thenRunAsync(() -> {
            System.out.println("Thread 2");
        });
    }
}