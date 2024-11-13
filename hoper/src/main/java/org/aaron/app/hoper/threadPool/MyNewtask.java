package org.aaron.app.hoper.threadPool;

import lombok.SneakyThrows;

public class MyNewtask implements Runnable {
    public MyNewtask(Integer personId) {
        this.personId = personId;
    }

    public Integer personId;

    @SneakyThrows
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("人员Id："+personId+"\n");
    }
}