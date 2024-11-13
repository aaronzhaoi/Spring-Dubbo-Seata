package org.aaron.app.hoper.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeStruct {

    /**
     * left指针指向right方向
     *
     * @param mytrees
     */
    public void doSetNext(TreePotNode mytrees) {

        Queue<TreePotNode> queue = new ArrayDeque<>();
        queue.offer(mytrees);
        while (!queue.isEmpty()) {
            TreePotNode lastNode = null;
            int queneSize = queue.size();
            for (int i = 1; i <= queneSize; i++) {
                TreePotNode root = queue.poll();
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
                if (i != 1) {
                    lastNode.next = root;
                }
                lastNode = root;

            }
        }
    }


    public void getRightPath(TreePotNode tree) {
        String path = null;

        while (tree.right != null) {
            path = path + String.valueOf(tree.right.value);
            tree = tree.right;
        }
    }

    /**
     * 获取每层的层级链表
     *
     * @param tree
     */
    public void getBfsList(TreePotNode tree) {
        Queue<TreePotNode> queue = new ArrayDeque<>();
        ArrayList<ArrayList> list = new ArrayList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            ArrayList groupList = new ArrayList();
            int queneSize = queue.size();
            for (int i = 1; i <= queneSize; i++) {
                TreePotNode node = queue.poll();
                groupList.add(node);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(groupList);
        }
    }


    /**
     * 将树转换成有节点的树
     *
     * @param tree
     */
    public void linkTrees(TreePotNode tree) {
        LinkedList<TreePotNode> linkedList = new LinkedList<>();
        bfs(tree, linkedList);

        for (int i = 1; i < linkedList.size(); i++) {
            TreePotNode preNode = linkedList.get(i - 1);
            TreePotNode currentNode = linkedList.get(i);
            preNode.left = null;
            preNode.right = currentNode;
        }

    }

    /**
     * 广度优先
     *
     * @param root
     * @param linkedList
     */
    public void bfs(TreePotNode root, LinkedList<TreePotNode> linkedList) {
        if (root == null) {
            return;
        }
        linkedList.add(root);
        bfs(root.left, linkedList);
        bfs(root.right, linkedList);
    }

    /**
     * 路径和
     *
     * @param root
     * @param sum
     * @param target
     * @return
     */
    public boolean xianxu(TreePotNode root, int sum, int target) {
        if (root == null) {
            return false;
        }
        if ((sum + root.value) == target) {
            return true;
        }
        ;
        if (xianxu(root.left, sum, target) || xianxu(root.right, sum, target)) {
            return true;
        }
        return false;

    }

    /**
     * 先序路径和
     *
     * @param root
     * @param sumList
     * @param current
     * @return
     */
    public int xianxuLujingHe(TreePotNode root, ArrayList<Integer> sumList, int current) {
        if (root == null) {
            sumList.add(current + root.value);
            return current + root.value;
        }
        current = current * 10;
        return xianxuLujingHe(root.left, sumList, current) + xianxuLujingHe(root.right, sumList, current);


    }


}

class TreePotNode {
    public TreePotNode(int value) {
        this.value = value;
    }

    int value;
    TreePotNode left;
    TreePotNode right;

    TreePotNode next;
}