package org.aaron.app.hoper.threaThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future阻塞主线程是因为Future是一种异步编程的方式，它允许在主线程中发起一个耗时的操作，并在后台线程中执行该操作。
 * 当主线程遇到Future的get()方法时，它会等待后台线程完成操作并返回结果，这个过程会阻塞主线程的执行。
 *
 * Future的阻塞主要有两种情况：
 *
 * 如果后台线程还没有完成操作，主线程调用Future的get()方法时会被阻塞，直到后台线程完成操作并返回结果。
 * 如果后台线程执行操作过程中发生异常，主线程调用Future的get()方法时也会被阻塞，直到后台线程抛出异常或者完成操作并返回结果。
 * 虽然Future阻塞主线程，但它的设计初衷是为了解决主线程阻塞的问题。通过将耗时的操作放在后台线程中执行，主线程可以继续执行其他任务，
 * 提高了程序的并发性和响应性。
 */

public class ThreadSequenceUsingExecutorService {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<?> future1 = executor.submit(() -> System.out.println("Thread 1"));
        try {
            future1.get(); // Wait for future1 to complete
            Future<?> future2 = executor.submit(() -> System.out.println("Thread 2"));
            future2.get(); // Wait for future2 to complete
            Future<?> future3 = executor.submit(() -> System.out.println("Thread 3"));
            future3.get(); // Wait for future3 to complete
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
