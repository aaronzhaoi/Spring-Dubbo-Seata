package org.aaron.app.hoper.quene;


import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;

public class MyArrayBlockingQueue {
    public static void main(String[] arg) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue(1);
        Mytask myTask = new Mytask();
        myTask.setArrayBlockingQueue(arrayBlockingQueue);
        Thread myThread = new Thread(myTask);
        myThread.start();

        try {
            arrayBlockingQueue.put(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            arrayBlockingQueue.put(9);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Data
class Mytask implements Runnable {
    private ArrayBlockingQueue<Integer> arrayBlockingQueue;

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(5000);
        while (!arrayBlockingQueue.isEmpty()) {
            try {
                Integer six = arrayBlockingQueue.take();
                System.out.print("取出的值：" + six + "\n");
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
