package org.aaron.app.hoper.algorithm;

public class StringAlg {

    public static void revertString() {
        String hello = "This is a   hello word";
        char[] hellChar = hello.toCharArray();
        StringBuffer newHello = new StringBuffer();
        int startPos = 0;
        int endPos = hellChar.length - 1;
        boolean tag = false;
        for (int i = hellChar.length - 1; i >= 0; i--) {
            if (i == hellChar.length - 1) {
                endPos = hellChar.length - 1;
            }
            if (String.valueOf(hellChar[i]).equals(" ") || i == 0) {
                startPos = i;
                tag = true;
            }

            if (startPos != endPos && tag) {
                for (int j = startPos; j <= endPos; j++) {
                    newHello.append(hellChar[j]);
                }
                endPos = startPos;
                tag = false;
            }
        }
        System.out.print(newHello.toString() + "\n");
    }


    public static boolean zixulie() {
        String a = "123456";
        String b = "3455";
        String[] newA = a.split(b);
        if (newA.length > 1) {
            System.out.print(true);
        } else {
            System.out.print(false);
        }
        return false;
    }

    public static boolean huiwen() {
        String tar = "aba";
        tar = tar.toLowerCase();
        int startPos = 0;
        int endPos = tar.length() - 1;
        while (startPos < tar.length() && endPos > 0) {
            char startStr = tar.charAt(startPos);
            while (!String.valueOf(startStr).matches("[a-zA-Z]+") && startPos < tar.length() - 1) {
                startPos++;
            }
            startStr = tar.charAt(startPos);

            char endStr = tar.charAt(endPos);
            while (!String.valueOf(endStr).matches("[a-zA-Z]+") && endPos > 0) {
                endPos--;
            }
            endStr = tar.charAt(endPos);
            if (startStr != endStr) {
                System.out.print(false);
                return false;
            }
            startPos++;
            endPos--;

        }
        System.out.print(true);
        return true;
    }

    public static void main(String[] args) {
        zixulie();
    }

}
