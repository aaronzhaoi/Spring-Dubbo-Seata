package org.aaron.app.hoper.locker;

public class SynonymObj {

    public synchronized void noStatic(int t) throws InterruptedException {
        if (t == 0) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.print("我是01: " + i + "\n");
            }
        } else {
            for (int i = 20; i < 30; i++) {
                Thread.sleep(1000);
                System.out.print("我是02: " + i + "\n");
            }
        }


    }

    public synchronized static void static2(int t) throws InterruptedException {
        if (t == 0) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.print("我是01: " + i + "\n");
            }
        } else {
            for (int i = 20; i < 30; i++) {
                Thread.sleep(1000);
                System.out.print("我是02: " + i + "\n");
            }
        }
    }

    public void noStatic3(int t) throws InterruptedException {
        synchronized (SynonymObj.class) {
            if (t == 0) {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.print("我是01: " + i + "\n");
                }
            } else {
                for (int i = 20; i < 30; i++) {
                    Thread.sleep(1000);
                    System.out.print("我是02: " + i + "\n");
                }
            }
        }
    }

    public synchronized void noStatic5(int t) throws InterruptedException {
            if (t == 0) {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.print("我是01: " + i + "\n");
                }
            } else {
                for (int i = 20; i < 30; i++) {
                    Thread.sleep(1000);
                    System.out.print("我是02: " + i + "\n");
                }
            }
    }

    public static void testLockMethod() {
        SynonymObj obj1 = new SynonymObj();
        SynonymObj obj2 = new SynonymObj();
        Thread objThread1 = new Thread() {
            public void run() {
                try {
                    System.out.print("第一次执行开始");
                    obj1.noStatic(0);
                    System.out.print("第一次执行结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread objThread2 = new Thread() {
            public void run() {
                try {
                    System.out.print("重复执行开始" + "\n");
                    obj1.noStatic5(1);
                    System.out.print("重复执行结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        objThread1.start();
        objThread2.start();
    }

    public static void testLockStatic() {

        Thread objThread1 = new Thread() {
            public void run() {
                try {
                    System.out.print("第一次执行开始");
                    System.out.print(Thread.currentThread().getName());
                    SynonymObj.static2(0);
                    System.out.print("第一次执行结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread objThread2 = new Thread() {
            public void run() {
                try {

                    System.out.print(Thread.currentThread().getName());
                    SynonymObj.static2(1);
                    System.out.print("方法执行结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        objThread1.start();
        objThread2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        testLockMethod();
    }


}
