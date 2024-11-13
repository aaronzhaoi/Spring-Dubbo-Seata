package org.aaron.hoper;

import org.aaron.app.hoper.aop.MyAopTestClient;
import org.aaron.app.hoper.except.MyExceptionTest;
import org.aaron.app.hoper.locker.MyBarrier;
import org.aaron.app.hoper.locker.MyCountDownLatch;
import org.aaron.app.hoper.locker.ReenLock;
import org.aaron.app.hoper.locker.Samphone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HoperApplicationTests {

    @Autowired
    public MyAopTestClient myAopTestClient;


    @Test
    void contextLoads() {
    }


    @Test
    void testAop() throws Exception {
        myAopTestClient.doAopTest("This is a Test");
    }

    @Test
    void testException() {
        MyExceptionTest.doException();
    }

    @Test
    void testReenLock() {
        ReenLock.testLock();
    }

    @Test
    void testCondition() throws InterruptedException {
        ReenLock.testCondition();
    }

    @Test
    void testSamphore() throws InterruptedException {
        Samphone.testSaxophone();
    }

    @Test
    void testCountDownLatch() throws InterruptedException {
        MyCountDownLatch.testCountDownLatch();
    }

    @Test
    void testCycbarrie() throws InterruptedException {
        MyBarrier myBarrier = new MyBarrier();
        myBarrier.testCycBarrier();
    }

}
