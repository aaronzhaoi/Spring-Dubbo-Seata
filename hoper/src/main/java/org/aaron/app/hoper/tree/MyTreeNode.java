package org.aaron.app.hoper.tree;

public class MyTreeNode {

    /**
     * 在使用时需要指定类型，否则会做类型擦除
     * @param myTree
     */
    private  void generate(TreeNewNode<Integer> myTree){

    }

}

class TreeNewNode<E> {
    private E e;
    private TreeNewNode<E> priorNode;
    private TreeNewNode<E> nextNode;

    private TreeNewNode<E> parentNode;

}


