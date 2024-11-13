package org.aaron.app.hoper.algorithm;

public class Qingwa {


    public static int tiao(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return tiao(n - 1) + tiao(n - 2);
    }

    public static void main(String[] args) {
        System.out.print(tiao(5));
    }
}
