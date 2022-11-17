package com.fj.tree;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/14 21:17    since 1.0.0
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
       BinaryTree binaryTree = new BinaryTree();
//        //创建节点
//        HeroNode root = new HeroNode(1, "宋江");
//        HeroNode node2 = new HeroNode(2, "吴用");
//        HeroNode node3 = new HeroNode(3, "卢俊义");
//        HeroNode node4 = new HeroNode(4, "林冲");
//        HeroNode node5 = new HeroNode(5, "关胜");
//        //手动创建二叉树
//        root.setLeft(node2);
//        root.setRight(node3);
//        node3.setRight(node4);
//        node3.setLeft(node5);
//        binaryTree.setRoot(root);
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(2, "jack");
        HeroNode node3 = new HeroNode(3, "smith");
        HeroNode node4= new HeroNode(4, "mary");
        HeroNode node5= new HeroNode(5, "king");
        HeroNode node6= new HeroNode(6, "homer");
        //创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        binaryTree.setRoot(root);
        binaryTree.postOrder();
/*        System.out.println("前序遍历");
        binaryTree.preOrder(); //1 2 3 5 4
        System.out.println("中序遍历");
        binaryTree.infixOrder(); //2 1 5 3 4
        System.out.println("后序遍历");
        binaryTree.postOrder();//2 5 4 3 1
        System.out.println("前序查找");
        HeroNode resNode = binaryTree.perOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到信息为no=%d name=%s\n",resNode.getNo(), resNode.getName());
        }
        System.out.println("中序查找");
        HeroNode resNode2 = binaryTree.infixOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到信息为no=%d name=%s\n",resNode2.getNo(), resNode2.getName());
        }
        System.out.println("后序查找");
        HeroNode resNode3 = binaryTree.postOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到信息为no=%d name=%s\n",resNode3.getNo(), resNode3.getName());
        }*/
        System.out.println("删除");
        binaryTree.delNode(3);
        binaryTree.preOrder();
    }

}
//定义一个二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        System.out.println("前序遍历");
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //中序遍历
    public void infixOrder(){
        System.out.println("中序遍历");
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //后序遍历
    public void postOrder(){
        System.out.println("后序遍历");
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //前序查找
    public HeroNode perOrderSearch(int no){
        System.out.println("前序查找");
        if (root!=null){
            return root.preOrderSearch(no);
        }
        return null;
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        System.out.println("中序查找");
        if (root!=null){
            return root.infixOrderSearch(no);
        }
        return null;
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        System.out.println("后序查找");
        if (root!=null){
            return root.postOrderSearch(no);
        }
        return null;
    }
    //删除结点
    public void delNode(int no){
        if (root!=null){
            //如果只有root一个节点
            if (root.getNo()==no){
                root=null;
            }else {
                //递归删除
                root.delete(no);
            }
        }else {
            System.out.println("空树");
        }

    }
}
//创建heroNode
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);//输出父节点
        //递归向左子树遍历
        if (this.left!=null) this.left.preOrder();
        //递归向右子树遍历
        if (this.right!=null) this.right.preOrder();
    }
    //中序遍历
    public void infixOrder(){
        //向左子树遍历
        if (this.left!=null) this.left.infixOrder();
        System.out.println(this);
        if (this.right!=null) this.right.infixOrder();
    }
    //后序遍历
    public void postOrder(){
        //递归向左子树遍历
        if (this.left!=null) this.left.postOrder();
        //递归向右子树遍历
        if (this.right!=null) this.right.postOrder();
        System.out.println(this);//输出父节点
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是
        if (this.no==no){
            return this;
        }
        //判断当前节点的左子节点是否为空
        //不为空则递归前序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode!=null){//不为空 说明在左子节点找到
            return resNode;
        }
        //左递归左子节点没有找到
        if (this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;//找到返回 没找到返回null
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode=null;
        //判断当前节点的左子节点是否为空
        //不为空则递归前序查找
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode!=null){//不为空 说明在左子节点找到
            return resNode;
        }

        //比较当前节点是不是
        if (this.no==no){
            return this;
        }
        //左递归左子节点没有找到
        if (this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;//找到返回 没找到返回null
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode=null;
        //判断当前节点的左子节点是否为空
        //不为空则递归前序查找
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode!=null){//不为空 说明在左子节点找到
            return resNode;
        }
        //左递归左子节点没有找到
        if (this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        if (resNode!=null){//不为空 说明在右子节点找到
            return resNode;
        }
        System.out.println("进入后序遍历查找");
        //比较当前节点是不是
        if (this.no==no){
            return this;
        }
        return resNode;//找到返回 没找到返回null
    }
    //递归删除节点
    //如果删除的是叶子节点 则删除该节点
    //如果删除的是非叶子节点 则删除该子树
    public void delete(int no){
        //如果当前结点的左子结点不为空，并且左子结点就是要删除的节点
        if (this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        //如果当前结点的右子结点不为空，并且右子结点就是要删除的节点
        if (this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        //向左子树进行递归删除
        if (this.left!=null){
            this.left.delete(no);
        }
        //向右子树进行递归删除
        if (this.right!=null){
            this.right.delete(no);
        }
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}