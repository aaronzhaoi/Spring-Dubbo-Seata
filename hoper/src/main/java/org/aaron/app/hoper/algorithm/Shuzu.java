package org.aaron.app.hoper.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Shuzu {


    private void sortList(int[] one, int[] two) {
        int[] three = new int[one.length + two.length];

        three = Arrays.copyOf(one, one.length + two.length);
        Arrays.sort(three);
        for (int i = 0; i <= one.length; i++) {
            three[i] = one[i];
        }

        for (int j = 0; j <= two.length; j++) {
            three[one.length] = two[j];
        }

        for (int k = 0; k <= three.length; k++) {
            int tmp = three[k];
            for (int t = k; t < three.length; t++) {
                if (three[k] > three[t]) {
                    three[k] = three[t];
                    three[t] = tmp;
                }
            }
        }

    }

    private void sortByTwo(int[] one, int[] two) {
        int[] three = new int[one.length + two.length];
        int post1 = 0;
        int post2 = 0;
        int post = 0;
        while (post1 < one.length && post2 < two.length) {
            if (one[post1] <= two[post2]) {
                three[post] = one[post1];
                post1++;
                post++;
                if (post1 > one.length) {
                    post--;
                    post--;
                }
                continue;
            } else if (one[post1] >= two[post2]) {
                three[post] = two[post2];
                post++;
                post2++;
                if (post2 > two.length) {
                    post2--;
                    post--;
                }
                continue;
            }
        }

        if (post1 <= post2) {
            for (int i = post1; i < two.length; i++) {
                three[i + 1] = two[i + 1];
            }
            return;
        }
        if (post1 > post2) {
            for (int i = post2; i < one.length; i++) {
                three[i + 1] = one[i + 1];
            }
            return;
        }

    }

    public void removeInt(int[] sz, int tar) {

        int post1 = 0;
        while (post1 < sz.length) {
            if (sz[post1] == tar) {
                int post2 = post1;
                int tmp = 0;
                while (sz[post2] == tar && post2 < sz.length) {
                    post2++;
                    if (sz[post2] != tar) {
                        tmp = sz[post2];
                    }
                }
                sz[post2] = sz[post1];
                sz[post1] = tmp;
                post1++;
            }
        }
    }

    public void removeSameData(int[] sz, int times) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < sz.length; i++) {
            set.add(sz[i]);
        }
        int[] retSz = new int[set.size()];
        int post1 = 0;
        int post = 0;
        while (post1 < sz.length) {
            int repTime = 0;
            for (int i = 0; i < post1; i++) {
                if (sz[i] == sz[post1]) {
                    repTime++;
                }
            }
            if (repTime <= times) {
                post++;
                retSz[post] = sz[post1];
            }
        }
    }


    public void duoShu(int[] sz) {
        int n = sz.length / 2;
        for (int i = 0; i < sz.length; i++) {
            int times = 0;
            for (int j = 0; j < sz.length; j++) {
                if (sz[i] == sz[j]) {
                    times++;
                }
            }
            if (times >= n) {
                System.out.print(sz[i]);
            }
        }
    }

    public void revert(int[] sz, int k) {
        int startPos = 0;
        int endPos = sz.length - 1;
        for (int i = 0; i < k; i++) {
            int tmp = sz[endPos];
            for (int j = endPos; j >= 0; j--) {
                sz[j] = sz[j - 1];
            }
            sz[i] = tmp;
        }

    }

    public boolean jump(int[] sz, int startPost) {
        int endValue = sz[startPost];
        boolean retflag = Boolean.FALSE;
        if (startPost + sz[startPost] >= sz.length) {
            retflag = true;
        } else if (startPost + 1 + sz[startPost + 1] >= endValue) {
            retflag = jump(sz, startPost + 1 + sz[startPost + 1]);
        } else {
            retflag = false;
        }
        return retflag;
    }

    /**
     * 第二次起跳时位置可以是从+1到end的位置，最远距离选择这一段中的最大值
     **/
    public int maxJump(int[] sz, int startPost, int times) {
        times++;
        int nextValuePost = startPost + sz[startPost];
        int tmp = startPost;
        int maxValue = sz[nextValuePost] + nextValuePost;
        for (int i = startPost + 1; i < nextValuePost; i++) {
            if (sz[i] + i > sz.length) {
                return times - 1;
            }
            if (sz[i] + i > maxValue && sz[i] + i > sz[tmp]) {
                tmp = i;
            }
        }
        maxJump(sz, tmp, times);
        return times;
    }

    public static void hzhishu(int[] sz) {
        for (int i = 0; i < sz.length; i++) {
            for (int j = i; j < sz.length; j++) {
                int midValue = sz[i];
                if (sz[i] > sz[j]) {
                    sz[i] = sz[j];
                    sz[j] = midValue;
                }
            }
        }
        for (int i = 0; i < sz.length; i++) {
            System.out.print(String.valueOf(sz[i]) + ',');
        }
        int pos = 0;
        for (int i = 0; i < sz.length; i++) {
            if (sz.length - i >= sz[i]) {
                if (i > pos) {
                    pos = i;
                }
            }
        }
        System.out.print(String.valueOf(sz[pos]) + ',');
    }

    public static void fatang() {
        int[] a = new int[]{1, 1, 1, 1, 1};
        int[] b = new int[]{1, 33, 32, 9, 10};
        // left to right
        for (int i = 0; i < a.length - 1; i++) {
            if (b[i + 1] > b[i]) {
                a[i + 1] = a[i] + 1;
            }
        }
        // right to left
        for (int i = a.length - 1; i > 0; i--) {
            if (b[i - 1] > b[i]) {
                a[i - 1] = a[i] + 1;
            }
            if (i - 2 >= 0 && b[i - 2] > b[i - 1]) {
                a[i - 2] = a[i - 2] + 1;
            }
        }

        System.out.print(a.toString());

    }


    public static void main(String[] args) {

        fatang();
    }
}
