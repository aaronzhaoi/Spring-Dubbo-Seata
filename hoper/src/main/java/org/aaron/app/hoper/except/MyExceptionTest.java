package org.aaron.app.hoper.except;

public class MyExceptionTest {

    public static void doException() {
        try {
            System.out.print("Here is Try\n");
            throw new Exception("test");
        } catch (Exception e) {
            System.out.print("Here is catch\n");
            return;
        } finally {
            System.out.print("Here is finally\n");
        }

    }
}


