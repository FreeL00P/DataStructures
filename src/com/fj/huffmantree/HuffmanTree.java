package com.fj.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/20 21:53    since 1.0.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root= createHuffmanTree(arr);
        preOrder(root);
    }
    //前序遍历方法
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("NULL Tree");
        }
    }
    //创建哈夫曼树的方法
    public static Node createHuffmanTree(int[] arr){
        //为了操作方便
        //1 遍历
        //2 将arr每个元素构成一个Node
        //3 将Node放入到ArrayList 中
        List<Node> nodes=new ArrayList<Node>();
        for (int value:arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //取出权值最小的两颗二叉树 （一个结点也可以看成一个最简单的二叉树）
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);
            //构建一颗新的二叉树
            Node parent=new Node(leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入Nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
//创建结点类
//为了让Node对象支持排序Collections集合
//让Node实现Comparable接口
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左子结点
    Node right;//指向右子结点
    //前序遍历
    public void  preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;//表示从小到大排序
    }
}