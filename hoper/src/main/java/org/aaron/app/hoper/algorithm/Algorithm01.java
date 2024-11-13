package org.aaron.app.hoper.algorithm;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Algorithm01 {

    ReentrantLock d = new ReentrantLock();
    Semaphore semaphore = new Semaphore(6);
    ThreadLocal threadLocal = new ThreadLocal();
    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    /**
     * 懒汉单例模式
     */
    private Algorithm01() {
    }

    //  静态常量，加载是被初始化
    private static final Algorithm01 algorithm = new Algorithm01();

    public static Algorithm01 getInstance() {
        return algorithm;
    }

    /**
     * 饿汉单例模式
     */
    private volatile static Algorithm01 algorithmLazy;

    private static Algorithm01 getInstanceLazy() {
        if (algorithmLazy == null) {
            synchronized (Algorithm.class) {
                if (algorithmLazy == null) {
                    algorithmLazy = new Algorithm01();
                }
            }
        }
        return algorithmLazy;
    }

    /**
     * 题目描述： 在一个二维数组中， 每一行都按照从左到右递增的顺序排序， 每一列
     * 都按照从上到下递增的顺序排序。 请完成一个函数， 输入这样的一个二维数组和一
     * 个整数， 判断数组中是否含有该整数。
     * 思路： 从右上角或左下角开始找， 逐行排除， 或者用二分法查找
     */
    // 双指针从有序数组查找数据
    public boolean searchData(int[][] searchData, int target) {
        int coloumnLength = searchData[0].length;
        int row = 0;

        while (row < searchData.length && coloumnLength >= 0) {
            if (searchData[row][coloumnLength] == target) {
                return true;
            }

            if (searchData[row][coloumnLength] > target) {
                coloumnLength--;
            }
            if (searchData[row][coloumnLength] < target) {
                row++;
            }
        }
        return false;
    }

    // 二分查找法，把多维数组想像成一维数组，通过左右游标查询
    public boolean findData(int[][] searchData, int target) {
        int left = 0;
        int coloumnLength = searchData[0].length;
        int right = coloumnLength * searchData.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            int midValue = searchData[mid / coloumnLength][mid % coloumnLength];

            if (midValue > target) {
                right = mid - 1;
            } else if (midValue < target) {
                left = mid + 1;
            } else if (midValue == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目描述： 将一个字符串中的空格替换成“%20”。 例如： 当字符串为 We Are
     * Happy.则经过替换之后的字符串为 We%20Are%20Happy。
     * 思路： 从后往前复制， 数组长度会增加， 或使用 StringBuilder、 StringBuffer 类8
     */

    public static String replaceStr(String str) {
        String newStr = str.replace(" ", "%20");
        return newStr;
    }

    public static String replaceStrV2(String str) {
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals(" ")) {
                newStr.append("%20");
            } else {
                newStr.append(String.valueOf(str.charAt(i)));
            }
        }
        return String.valueOf(newStr);
    }

    /**
     * 题目描述： 输入一个链表， 从尾到头打印链表每个节点的值。
     * 思路： 借助栈实现， 或使用递归的方法。
     */
    // 使用Stack
    private static void printList() {
        String[] str = {"asd", "asfda", "dsa", "12"};
        Stack<String> stringStack = new Stack<>();
        Map<String, String> stringMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            stringStack.push(str[i]);
        }
        while (!stringStack.isEmpty()) {
            String value = stringStack.pop();
            stringMap.put(value, value);
        }

        for (String val : stringMap.keySet()) {
            System.out.print(stringMap.get(val) + "\n");
        }
    }

    // 使用递归
    private static void printListv2(List<String> str, List<String> stringList) {
        int length = str.size();
        if (length > 0) {
            stringList.add(str.get(length - 1));
            str = str.subList(0, length - 1);
            printListv2(str, stringList);
        }
    }

    /**
     * 题目描述： 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之为数组的
     * 旋转。 输入一个非递减排序的数组的一个旋转， 输出旋转数组的最小元素。 例如
     * 数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转， 该数组的最小值为 1。 NOTE： 给出的所
     * 有元素都大于 0， 若数组大小为 0， 请返回-1。 假设数组中不存在重复元素。
     * 思路： 利用二分法， 找到数组的中间元素 mid。 如果中间元素 > 数组第一个元素，
     * 在 mid 右边搜索变化点。 如果中间元素 < 数组第一个元素， 我们需要在 mid 左边
     * 搜索变化点。 当找到变化点时停止搜索， 满足 nums[mid] > nums[mid + 1]
     * （mid+1 是最小值） 或 nums[mid - 1] > nums[mid]（ mid 是最小值） 即可。
     */
    private int getMin(int[] ints) {
        int left = 0;
        int right = ints.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (ints[mid] > ints[mid + 1]) {
                return ints[mid + 1];
            }
            if (ints[mid - 1] < ints[mid]) {
                return ints[0];
            }

            if (ints[mid] > ints[0]) {
                right = mid - 1;
            }
            if (ints[mid] < ints[0]) {
                left = mid;
            }
        }
        return -1;
    }

    private int getMinFormLeft(int[] ints) {
        int left = 0;
        int right = ints.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (ints[mid] > ints[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return ints[left];
    }

    /**
     * 9.1 输出斐波那契数列的第 n 项
     * 题目描述： 现在要求输入一个整数 n， 请你输出斐波那契数列的第 n 项。 n<=39
     * 思路： 递归的效率低， 使用循环方式
     */
    private int getFbnx(int n) {
        int first = 0;
        int second = 1;
        int pre = first;
        int preTwo = second;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre + preTwo;
            preTwo = pre;
            pre = result;
        }
        return result;
    }

    class ListNode {
        private int val;

        public int getVal() {
            return val;
        }

        public ListNode getPreNode() {
            return preNode;
        }

        public ListNode getNextNode() {
            return nextNode;
        }

        private ListNode preNode;

        public void setVal(int val) {
            this.val = val;
        }

        public void setPreNode(ListNode preNode) {
            this.preNode = preNode;
        }

        public void setNextNode(ListNode nextNode) {
            this.nextNode = nextNode;
        }

        private ListNode nextNode;
    }

    // 快慢指针
    private ListNode findNode(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        LinkedList<String> test = new LinkedList<>();
        while (k > 0) {
            fast = fast.nextNode;
        }
        while (fast.nextNode != null) {
            fast = fast.nextNode;
            slow = slow.nextNode;
        }
        return slow;
    }

    // 反转指针
    private ListNode reverse(ListNode head) {
        ListNode retNode = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode nodeTmp = currentNode.nextNode;
            currentNode.nextNode = retNode;
            retNode = currentNode;
            currentNode = nodeTmp;
        }
        return retNode;
    }

    /**
     * 判断二叉树 A 中是否包含子树 B
     */
   /* public boolean hasSubTree(TreeNode source, TreeNode target) {
        if (target == null) {
            return true;
        }
        if (source == null) {
            return false;
        }
        if (doesTree1HaveTree2(source, target)) {
            return true;
        }
        return hasSubTree(source..left,target)||hasSubTree(source.right, target);
    }

    public static boolean doesTree1HaveTree2(TreeNode source, TreeNode target) {
        if (source == null && target == null) {
            return true;
        }
        if (source == null || target == null) {
            return false;
        }
        if (source.val != target.val) {
            return false;
        }
        return doesTree1HaveTree2(source.left, target.left) && doesTree1HaveTree2(source.right, target.right);
    }*/
    private static String[] splitString(String str) {
        String[] strs = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            String toStr = String.valueOf(chr);
            strs[i] = toStr;
        }
        List<String> strList = new ArrayList<>(Arrays.asList(strs));
        return strs;
    }

    private static List<Integer> circle(int[][] ints) {
        List<Integer> integerList = new ArrayList<>();

        int left = 0;
        int right = ints[1].length;
        int btm = ints.length;
        int top = 0;

        while (left < right && top < btm) {

            // 从左到右计算
            for (int i = left; i <= right; i++) {
                integerList.add(Integer.valueOf(ints[top][i]));
            }

            // 从上到下计算
            for (int i = top; i <= btm; i++) {
                integerList.add(Integer.valueOf(ints[i][right]));
            }

            // 从右到左
            for (int i = right; i >= left; i--) {
                integerList.add(Integer.valueOf(ints[btm][i]));
            }

            // 从下到上
            for (int i = btm; i >= top; i--) {
                integerList.add(Integer.valueOf(ints[btm][i]));
            }
        }
        return integerList;
    }

    /**
     * 数组中两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        int currentValue = 0;
        int nextValue = 0;
        int[] ret = new int[1];
        for (int i = 0; i < nums.length - 1; i++) {
            currentValue = nums[i];
            for (int j = i; i < nums.length - 1; i++) {
                nextValue = nums[j];
                if (currentValue + nextValue == target) {
                    ret[0] = i;
                }
            }
        }
        return ret;
    }

    /**
     * 整数反转
     */
    public int reverse(int x) {
        String value = String.valueOf(x);
        char[] charValue = value.toCharArray();
        StringBuffer newValue = null;
        for (int i = charValue.length - 1; i >= 0; i--) {
            newValue.append(String.valueOf(charValue[i]));
        }
        String strValue = newValue.toString();
        int intValue = Integer.valueOf(strValue);
        return intValue;
    }

    /**
     * 回文字符
     */
    public String huiWen(String str) {
        StringBuffer strBf = new StringBuffer(str);
        StringBuffer newBf = strBf.reverse();
        String newStr = newBf.toString();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != newStr.charAt(i)) {
                count++;
            }
        }
        if (count > 0) {
            return "Y";
        } else {
            return "N";
        }
    }

    class Point {
        int xVlue;
        int yValue;

        public void point(int x, int y) {
            this.xVlue = x;
            this.yValue = y;
        }
    }

    /**
     * 木桶，计算面积
     */
    private void sumArea(List<Point> points) {
        int i = 0;
        int j = points.size() - 1;
        int area = 0;
        Point pointL;
        Point pointH;
        while (i < j) {
            Point left = points.get(i);
            Point right = points.get(j);
            int longX = right.xVlue - left.xVlue;
            int highY = Math.min(right.yValue, left.yValue);
            int areaNew = longX * highY;
            if (areaNew > area) {
                pointL = left;
                pointH = right;
            }
            if (left.yValue > right.yValue) {
                j--;
            } else {
                i++;
            }

        }

    }

    /**
     * 最长公共字符串
     */
  /*  public String longestCommonPrefix(String[] strs) {
        String intial=strs[0];
        for(int i=1;i<strs.length;i++)

    }*/

    /**
     * 三数之和
     */
    public static void threeSum() {
        int[] numbers = {-1, -6, -9, 3, 2, 2, 4, 10};
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                break;
            }
            if (numbers[i] == numbers[i + 1]) {
                continue;
            }
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] == numbers[i] * -1) {
                    System.out.print("找到数据" + numbers[i] + "  " + numbers[left] + "  " + numbers[right]);
                    left++;
                }
                if (numbers[left] + numbers[right] > numbers[i] * -1) {
                    right--;
                }
                if (numbers[left] + numbers[right] < numbers[i] * -1) {
                    left++;
                }
            }
        }
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    public static boolean isValid(String strs) {
        char[] chars = strs.toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == "(".toCharArray()[0] || chars[i] == "{".toCharArray()[0] || chars[i] == "["
                    .toCharArray()[0]) {
                stack.push(chars[i]);
            } else {
                if (chars[i] == ")".toCharArray()[0]) {
                    if (stack.pop().toString() != "(") {
                        return false;
                    }
                }
                if (chars[i] == "}".toCharArray()[0]) {
                    if (stack.pop().toString() != "{") {
                        return false;
                    }
                }
                if (chars[i] == "]".toCharArray()[0]) {
                    if (stack.pop().toString() != "[") {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    class ListNodeNew {
        int val;
        ListNodeNew next;

        ListNodeNew(int x) {
            val = x;
        }
    }

    public static ListNodeNew mergeListNode(ListNodeNew listOne, ListNodeNew listTwo) {
        if (listOne == null) {
            return listOne;
        }
        if (listTwo == null) {
            return listTwo;
        }
        if (listTwo.val > listOne.val) {
            listOne.next = mergeListNode(listOne.next, listTwo);
            return listOne;
        }
        if (listTwo.val < listOne.val) {
            listTwo.next = mergeListNode(listTwo.next, listOne);
            return listTwo;
        }
        return listTwo;
    }

    /**
     * 给你一个有序数组 nums ，
     * 请 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度
     */
    public static int removeDup(int[] nums) {
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[j]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    /**
     * 最长公共前缀
     **/
    public static String getCommomStr() {
        String str1 = "Flow";
        String str2 = "Flowewr";
        String str3 = "Flower";
        int retInt = 0;
        int length = Math.min(Math.min(str1.length(), str2.length()), str3.length());
        for (int i = 0; i < length; i++) {
            if (str1.substring(0, i).equals(str2.substring(0, i)) && str1.substring(0, i)
                    .equals(str3.substring(0, i))) {
                retInt = i;
                continue;
            } else {
                retInt = i;
                break;
            }
        }
        String commonStr = str1.substring(0, retInt + 1);
        return commonStr;
    }

    /***
     * 最接近的值
     * */
    public int[] threeSumClose(int[] nums, int target) {

        Arrays.sort(nums);
        int[] data = new int[3];
        int length = nums.length;
        int closer = 99999;
        for (int i = 0; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                break;
            }
            List<Integer> one = new ArrayList<>();
            int left = i + 1;
            int right = length - 1;
            int preLeft = 0;
            int preRight = 0;
            int preSum = 0;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    data[0] = i;
                    data[1] = left;
                    data[2] = right;
                } else if (sum < target) {
                    preLeft = left;
                    preSum = sum;
                    left++;
                } else if (sum < target) {
                    preRight = right;
                    preSum = sum;
                    right--;
                }

                if (Math.abs(target - sum) > Math.abs(target - preSum)) {
                    data[0] = i - 1;
                    data[1] = preLeft;
                    data[2] = preRight;
                } else {
                    data[0] = i;
                    data[1] = left;
                    data[2] = right;
                }
            }

        }
        return data;
    }

    public static void getData() {
        int[] aa = new int[]{0, 7, 2, 9};
        Arrays.sort(aa);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.print("wo shi thread" + Thread.currentThread().getId() + "\n");
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.print("我是Runable" + Thread.currentThread().getId() + "\n");
        }
    }

    class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            System.out.print("我是Callable" + Thread.currentThread().getId() + "\n");
            return Integer.valueOf(8);
        }
    }

    public void printData() {
        System.out.print("This is CompletableFuture");
    }

    public String retData(int data) {
        System.out.print("\n" + "我是线程池ID：" + Thread.currentThread().getId() + "\n");
        return data + 8 + "\n";
    }

    public void doMulThreadPools(Algorithm01 algorithm) throws ExecutionException, InterruptedException {
        ExecutorService threadPools = Executors.newFixedThreadPool(5);
        List<CompletableFuture<String>> strRestS = new ArrayList<>();
        // 无返回值
        CompletableFuture<Void> voidRet = CompletableFuture.runAsync(() -> {
            algorithm.printData();
        }, threadPools);
        // 有返回值
        for (int i = 0; i < 10; i++) {
            int varData = i;
            CompletableFuture<String> retStr =
                    CompletableFuture.supplyAsync(() -> algorithm.retData(varData), threadPools);
            strRestS.add(retStr);
        }
        for (CompletableFuture<String> vo : strRestS) {
            System.out.print(vo.get());
        }
        threadPools.shutdown();
    }

    public int qingWa(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return qingWa(n - 1) + qingWa(n - 2);
        }
    }

    public List<String> digui(int n) {
        List<String> strList = new ArrayList<>();
        int test = n;
        if (n > 25) {
            System.out.print("***********************\n");
            strList.add(String.valueOf(test));
            System.out.print(test + "   " + strList.size());
            return strList;
        } else {
            test++;
            strList.add(String.valueOf(test));
            strList.addAll(digui(test));
            return strList;
        }
    }

    public List<String> add(int i) {
        List<String> myList = new ArrayList<>();
        if (i == 1) {
            String test = String.valueOf(i);
            myList.add(test);
            return myList;
        } else {
            String test = String.valueOf(i);
            myList.add(test);
            myList.addAll(add(i - 1));
            return myList;
        }
    }

    @Data
    class QingWaTiaoNode {
        int currentValue;
        QingWaTiaoNode leftNode;
        QingWaTiaoNode rightNode;

        public QingWaTiaoNode() {
        }

        public QingWaTiaoNode(int currentValue, QingWaTiaoNode leftNode,
                              QingWaTiaoNode rightNode) {
            this.currentValue = currentValue;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    public QingWaTiaoNode generate(int n, int needValue) {
        QingWaTiaoNode qingWaTiaoNode = new QingWaTiaoNode();
        if (n == 0 && (needValue == 1 || needValue == 2)) {
            qingWaTiaoNode = new QingWaTiaoNode(needValue, null, null);
            return qingWaTiaoNode;
        } else {
            QingWaTiaoNode left = generate(n - 1, 1);
            QingWaTiaoNode right = generate(n - 1, 2);
            return new QingWaTiaoNode(needValue, left, right);
        }
    }

    private void preorder2(QingWaTiaoNode root, LinkedList<Integer> ll, LinkedList<LinkedList<Integer>> list, int
            target, int currentValue) {
        if (root == null) {
            return;
        }
        int newValue = currentValue + root.getCurrentValue();
        ll.add(root.getCurrentValue());
        if (target == newValue) {
            LinkedList temp1 = ObjectUtils.clone(ll);
            list.add(temp1);
            return;
        }
        if (currentValue > target) {
            return;
        }
        QingWaTiaoNode left = root.getLeftNode();
        QingWaTiaoNode right = root.getRightNode();
        preorder2(left, ll, list, target, newValue);
        ll.remove(ll.size() - 1);
        preorder2(right, ll, list, target, newValue);
        ll.remove(ll.size() - 1);
    }

    /**
     * 树的分治算法
     */
    public List<List<Integer>> doTest(QingWaTiaoNode root, int target, int currentValue) {
        List<List<Integer>> retList = new ArrayList<>();
        int newValue = currentValue + root.getCurrentValue();
        List<Integer> tmpList = new ArrayList<>();
        if (target == newValue) {
            tmpList.add(root.getCurrentValue());
            retList.add(tmpList);
            return retList;
        }
        if (currentValue > target) {
            return retList;
        }
        List<List<Integer>> leftList = doTest(root.getLeftNode(), target, newValue);
        for (List<Integer> ll : leftList) {
            ll.add(root.getCurrentValue());
            retList.add(ll);
        }
        List<List<Integer>> rightList = doTest(root.getRightNode(), target, newValue);
        for (List<Integer> ll : rightList) {
            ll.add(root.getCurrentValue());
            retList.add(ll);
        }
        return retList;
    }

    /**
     * 树的分支算法,
     */
    public List<Integer> rootStart(QingWaTiaoNode root) {
        List<Integer> rootList = new ArrayList<>();
        if (root == null) {
            return rootList;
        }
        rootList.add(root.getCurrentValue());

        List<Integer> left = rootStart(root.getLeftNode());
        if (CollectionUtils.isNotEmpty(left)) {
            rootList.addAll(left);
        }

        List<Integer> right = rootStart(root.getRightNode());
        if (CollectionUtils.isNotEmpty(left)) {
            rootList.addAll(right);
        }
        return rootList;
    }

    /**
     * 分支算法
     */
    public int getDeep(QingWaTiaoNode root) {
        if (root.getRightNode() == null && root.getLeftNode() == null) {
            return 0;
        }
        int leftDeep = getDeep(root.getRightNode());
        int rightDeep = getDeep(root.getRightNode());
        return Math.max(leftDeep, rightDeep) + 1;
    }

    /**
     * 分支算法
     */
    public void getDeepParam(QingWaTiaoNode root, List<Integer> deepList, int deep) {
        if (root == null) {
            deepList.add(deep - 1);
            return;
        }
        int leftDeep = deep + 1;
        int rightDeep = deep + 1;
        getDeepParam(root.getRightNode(), deepList, leftDeep);
        getDeepParam(root.getLeftNode(), deepList, rightDeep);
    }

    public void suanFa(int n, int target) {
        QingWaTiaoNode left = generate(n, 1);
        QingWaTiaoNode right = generate(n, 2);
        QingWaTiaoNode qingWaTiaoNode = new QingWaTiaoNode(0, left, right);
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        LinkedList<Integer> ll = new LinkedList<>();
        preorder2(qingWaTiaoNode, ll, list, 5, 0);
        List<List<Integer>> tt = doTest(qingWaTiaoNode, 5, 0);
        List<Integer> rootStart = rootStart(qingWaTiaoNode);
        System.out.print(getDeep(qingWaTiaoNode) + "\n");
        int deep = 0;
        int newV = 0;
        List<Integer> deepList = new ArrayList<>();
        getDeepParam(qingWaTiaoNode, deepList, deep);
        System.out.print("遍历算法" + deep + "\n");
        int a = 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
     /*   String[] str = {"asd", "asfda", "dsa", "12"};
        List<String> stringList = new ArrayList<>(Arrays.asList(str));
        List<String> stringListTest = new ArrayList<>();
        Algorithm01.printListv2(stringList, stringListTest);*/
       /* String[] retStr = Algorithm01.splitString("dsa");
        System.out.print("      ");*/
        Algorithm01 algorithm = new Algorithm01();
        algorithm.suanFa(5, 5);
       /* ExecutorService myPools = Executors.newFixedThreadPool(2);
        MyThread myThread = algorithm.new MyThread();
        myThread.start();
        MyRunnable myRunnable = algorithm.new MyRunnable();
        //  myRunnable.run();
        new Thread(myRunnable).start();
        myPools.submit(myRunnable);
        MyCallable myCallable = algorithm.new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(myCallable);
        //   task.run();
        new Thread(task, "task").start();
        Future<Integer> t = myPools.submit(myCallable);
        System.out.print(t.get());
        CompletableFuture<Void> test = CompletableFuture.runAsync(() -> {
            algorithm.printData();
        }, myPools);
        algorithm.doMulThreadPools(algorithm);
        algorithm.digui(5);*/
        System.out.print("wo shi di gui****************\n");
        List<String> ll = algorithm.add(3);
        System.out.print("wo shi di gui****************\n");
    }
}
