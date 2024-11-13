package org.aaron.app.hoper.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MyTrees {

    public Integer getValues() {
        return values;
    }

    public void setValues(Integer values) {
        this.values = values;
    }

    public MyTrees getParentNode() {
        return parentNode;
    }

    public void setParentNode(MyTrees parentNode) {
        this.parentNode = parentNode;
    }

    public MyTrees getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(MyTrees leftNode) {
        this.leftNode = leftNode;
    }

    public MyTrees getRightNode() {
        return rightNode;
    }

    public void setRightNode(MyTrees rightNode) {
        this.rightNode = rightNode;
    }

    private Integer values;
    private MyTrees parentNode;

    public MyTrees leftNode;

    public MyTrees rightNode;

    public boolean beLeaf() {
        return true;
    }

    private MyTrees getLeftLeaf(MyTrees leftNode) {
        if (leftNode.beLeaf()) {
            return leftNode;
        }
        return getLeftLeaf(leftNode.getLeftNode());

    }

    private MyTrees getRightLeaf(MyTrees rightNode) {
        if (rightNode.beLeaf()) {
            return rightNode;
        }
        return getRightLeaf(rightNode.getLeftNode());
    }

    /**
     * 中序排列
     * 递归是在返回途中获取到路径信息
     *
     * @param trees
     * @param path
     */
    public void zhongxu(MyTrees trees, StringBuffer path) {
        MyTrees left = trees.getLeftNode();
        if (left != null) {
            zhongxu(left, path);
        }
        path.append(trees.getValues() + "->");
        MyTrees right = trees.getRightNode();
        if (right != null) {
            zhongxu(right, path);
        }
    }

    /**
     * 先序排列
     *
     * @param tree
     * @param path
     */
    public void xianxu(MyTrees tree, StringBuffer path) {

        path.append(tree.getValues() + "->");

        MyTrees left = tree.getLeftNode();
        if (left != null) {
            xianxu(left, path);
        }

        MyTrees right = tree.getRightNode();
        if (right != null) {
            xianxu(right, path);
        }

    }

    /**
     * 后序排列
     *
     * @param tree
     * @param path
     */
    public void houxu(MyTrees tree, StringBuffer path) {

        MyTrees left = tree.getLeftNode();
        if (left != null) {
            xianxu(left, path);
        }

        MyTrees right = tree.getRightNode();
        if (right != null) {
            xianxu(right, path);
        }
        path.append(tree.getValues() + "->");

    }

    /**
     * 广度优先
     *
     * @param tree
     * @param path
     */
    public void bfs(MyTrees tree, StringBuffer path) {
        Queue<MyTrees> q = new LinkedList<>();
        q.add(tree);
        while (!q.isEmpty()) {
            MyTrees queue = q.poll();
            path.append(queue.getValues() + "->");
            if (queue.getLeftNode() != null) {
                q.add(queue.leftNode);
            }
            if (queue.getRightNode() != null) {
                q.add(queue.getRightNode());
            }

        }
    }

    /**
     * 两树是否相同
     */

    public boolean checkSameTrees(MyTrees left, MyTrees right) {
        if (left.getValues().equals(right.getValues())) {
            if (checkSameTrees(left.getLeftNode(), right.getLeftNode())) {
                return checkSameTrees(left.getLeftNode(), getRightNode());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 二叉树翻转
     */
    public void revertTress(MyTrees myTrees) {
        MyTrees leftNode = myTrees.getLeftNode();
        MyTrees rightNode = myTrees.getRightNode();

        myTrees.setLeftNode(rightNode);
        myTrees.setRightNode(leftNode);
        revertTress(myTrees.getLeftNode());
        revertTress(myTrees.getLeftNode());

    }

    /**
     * 先序排列生成二叉树
     *
     * @return
     */
    public MyTrees doTrees(int[] sz) {
        MyTrees root = new MyTrees();
        root.setValues(sz[0]);
        Queue<MyTrees> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (index < sz.length) {
            MyTrees trees = queue.poll();
            MyTrees leftTrees = trees.getLeftNode();
            leftTrees.setValues(sz[index]);
            queue.offer(leftTrees);
            index++;
            MyTrees rightTrees = trees.getRightNode();
            leftTrees.setValues(sz[index]);
            queue.offer(rightTrees);
        }
        return root;
    }

    /**
     * 根据后序和中序生成二叉树
     *
     * @param
     * @return
     */
    public static void main(String[] args) {
        DoErChashu doErChashu = new DoErChashu();
        // doErChashu.doZhongXuAfterTress();
        doErChashu.doZhongXuPreTress();
    }

}

class DoErChashu {
    Map<Integer, Integer> zxOrderMap = new HashMap<>();
    int postIndex;

    public void doZhongXuAfterTress() {

        int[] zxOrder = {9, 3, 15, 20, 7};
        int[] afterOrder = {9, 15, 7, 20, 3};

        for (int i = 0; i < zxOrder.length; i++) {
            zxOrderMap.put(zxOrder[i], i);
        }
        int afterIndex = afterOrder.length - 1;
        postIndex = afterIndex;
        MyTrees root = doNode(zxOrder, afterOrder, 0, afterIndex);
    }

    public void doZhongXuPreTress() {

        int[] zxOrder = {9, 3, 15, 20, 7};
        int[] preOrder = {3,9,20,15,7};

        for (int i = 0; i < zxOrder.length; i++) {
            zxOrderMap.put(zxOrder[i], i);
        }
        int afterIndex = preOrder.length - 1;
        postIndex = 0;
        MyTrees root = doNodePre(zxOrder, preOrder, 0, afterIndex);
    }

    /**
     * 后续+中序
     * @param zxOrder
     * @param afterOrder
     * @param left
     * @param right
     * @return
     */
    public MyTrees doNode(int[] zxOrder, int[] afterOrder, int left, int right) {
        if (left > right) {
            return null;
        }

        int rootValue = afterOrder[postIndex];
        MyTrees root = new MyTrees();
        root.setValues(rootValue);
        postIndex--;
        int rootIndex = zxOrderMap.get(rootValue);

        root.rightNode = doNode(zxOrder, afterOrder, rootIndex + 1, right);

        root.leftNode = doNode(zxOrder, afterOrder, left, rootIndex - 1);

        return root;
    }

    /**
     * 前序+中序构造
     * @param zxOrder
     * @param preOrder
     * @param left
     * @param right
     * @return
     */
    public MyTrees doNodePre(int[] zxOrder, int[] preOrder, int left, int right) {
        if (left > right) {
            return null;
        }

        int rootValue = preOrder[postIndex];
        MyTrees root = new MyTrees();
        root.setValues(rootValue);
        postIndex++;
        int rootIndex = zxOrderMap.get(rootValue);

        root.leftNode = doNodePre(zxOrder, preOrder, left, rootIndex - 1);

        root.rightNode = doNodePre(zxOrder, preOrder, rootIndex + 1, right);
        return root;
    }

    public void doPreAfterTrees(int[] preOrder, int[] afterOrder){

    }


}


class MyNode {
    private Integer values;

}
