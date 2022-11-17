package com.fj.tree.ThreadedBinaryTree;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/17 20:57    since 1.0.0
 * 中序线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能 以数组｛8, 3, 10, 1, 14, 6｝为例

        /**
         *          1
         *        /   \
         *       3     6
         *      / \   /
         *     8  10 14
         */

        HeroNode root = new HeroNode(1, "java");
        HeroNode node2 = new HeroNode(3, "C#");
        HeroNode node3 = new HeroNode(6, "Python");
        HeroNode node4 = new HeroNode(8, "C++");
        HeroNode node5 = new HeroNode(10, "GO");
        HeroNode node6 = new HeroNode(14, "Dephi");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

//        //*************测试中序线索化***************//
//        System.out.println("==========中序线索化开始=============");
//        System.out.println("｛8, 3, 10, 1, 14, 6｝");
//        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
//        threadedBinaryTree.setRoot(root);
//        threadedBinaryTree.infixThreadedNodes();
//
//        //测试: 以10号节点测试
//        HeroNode leftNode = node5.getLeft();
//        HeroNode rightNode = node5.getRight();
//        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
//        System.out.println("10号结点的后继结点是=" + rightNode); //1
//
//        //当线索化二叉树后，能在使用原来的遍历方法
//        //threadedBinaryTree.infixOrder();
//        System.out.println("中序使用线索化的方式遍历 线索化二叉树");
//        threadedBinaryTree.infixThreadList(); // 8, 3, 10, 1, 14, 6
//        //********************中序结束******************//


        //******************前序*****************//
//        System.out.println("==========前序线索化开始=============");
//        System.out.println("{1,3,8,10,6,14}");
//
//        //前序：{1,3,8,10,6,14}
//        ThreadedBinaryTree threadedBinaryTreePre = new ThreadedBinaryTree();
//        threadedBinaryTreePre.setRoot(root);
//        threadedBinaryTreePre.preThreadedNodes();
//
//        //测试: 以10号节点测试
//        HeroNode leftNodePre = node4.getLeft();
//        HeroNode rightNodePre = node4.getRight();
//        System.out.println("8号结点的前驱结点是 =" + leftNodePre); //3
//        System.out.println("8号结点的后继结点是=" + rightNodePre); //10
//
//        HeroNode leftNodetenPre = node5.getLeft();
//        HeroNode rightNodetenPre = node5.getRight();
//        System.out.println("10号结点的前驱结点是 =" + leftNodetenPre); //8
//        System.out.println("10号结点的后继结点是=" + rightNodetenPre); //6
//
//        System.out.println("前序使用线索化的方式遍历 线索化二叉树");
//        threadedBinaryTreePre.preThreadedList();//{1,3,8,10,6,14}
//
//
//        //******************前序结束*****************//
//
        //******************后序*****************//

        //如果是后序，需要创建二叉树的时候，将parent进行保存。这个是用于后续二叉树的遍历的

        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);

        System.out.println("==========后序线索化开始=============");
        System.out.println("{8,10,3,14,6,1}");
        //后序：{8,10,3,14,6,1}
        ThreadedBinaryTree threadedBinaryTreeAfter = new ThreadedBinaryTree();
        threadedBinaryTreeAfter.setRoot(root);
        threadedBinaryTreeAfter.postThreadedNodes();


//        HeroNode leftNodeAfter = node4.getLeft();
//        HeroNode rightNodeAfter = node4.getRight();
//        System.out.println("8号结点的前驱结点是 =" + leftNodeAfter); //null
//        System.out.println("8号结点的后继结点是=" + rightNodeAfter); //10
//
//        HeroNode leftNodetenAfter = node5.getLeft();
//        HeroNode rightNodetenAfter = node5.getRight();
//        System.out.println("10号结点的前驱结点是 =" + leftNodetenAfter); //8
//        System.out.println("10号结点的后继结点是=" + rightNodetenAfter); //3

        System.out.println("后序使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTreeAfter.postThreadList();//{8,10,3,14,6,1}

    }
}
//定义一个二叉树
class ThreadedBinaryTree{
    private HeroNode root;
    //为了实现线索化 需要创建指向当前节点的前驱节点的指针
    private HeroNode pre=null;//在递归进行线索化时 pre总是保留前一个节点
    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //重载
    public void infixThreadedNodes(){
        this.infixThreadedNodes(root);
    }
    public void preThreadedNodes(){this.preThreadedNodes(root);}
    public void postThreadedNodes(){this.postThreadedNodes(root);}
    //编写对二叉树前序线索化的方法
    public void preThreadedNodes(HeroNode node){ //124536
        if (node==null) return;
        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            //当当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点指向的前驱节点的类型
            node.setLeftType(1);
        }

        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        if (node.getLeftType()!=1){
            preThreadedNodes(node.getLeft());
        }
        if (node.getRightType()!=1){
            preThreadedNodes(node.getRight());
        }

    }
    //编写对二叉树中序线索化的方法
    public void infixThreadedNodes(HeroNode node){//node-->当前需要线索化的节点
        if (node==null) {
            return;
        }
        //先线索化左子树
        infixThreadedNodes(node.getLeft());
        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            //当当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点指向的前驱节点的类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre!=null&&pre.getRight()==null){
            //让前驱节点的后指针实现当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前结点是下一个节点的前驱节点
        pre=node;
        //线索化右子树
        infixThreadedNodes(node.getRight());
    }
    //编写对二叉树后序线索化的方法
    public void postThreadedNodes(HeroNode node){// 452631
        //如果node==null, 不能线索化
        if (node == null) {
            return;
        }

        //(一)先线索化左子树
        postThreadedNodes(node.getLeft());
        //(三)再线索化右子树
        postThreadedNodes(node.getRight());


        //左指针为空,将左指针指向前驱节点
        //8结点的.left = 上一个节点 , 8结点的.leftType = 1
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点,是下一次进行处理，有点不好理解
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

    }
    //前序线索化二叉树遍历
    public void preThreadedList(){
        HeroNode node=root;//存储当前遍历结点 从root开始
        while (node!=null){
            while (node.getLeftType()==0) {
                System.out.println(node);//如果是叶子节点 非前驱节点直接输出
                node=node.getLeft();
            }
            System.out.println(node);
            //替换这个遍历的节点
            node=node.getRight();
        }
    }
    //中序线索化二叉树遍历
    public void infixThreadList(){
        //定义一个变量 存储当前遍历的结点 从root开始
        HeroNode node=root;
        while(node!=null){
            //循环找到leftType==1的结点
            //后面随着遍历而变化 leftType==1-->说明该结点的按照线索化处理后的有效结点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前结点
            System.out.println(node);
            //如果当前结点的有指针指向的后继节点 就一直输出
            while (node.getRightType()==1){
                //获取当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node=node.getRight();
        }
    }
    //后序线索化二叉树遍历
    public void postThreadList(){
        //1、找后序遍历方式开始的节点
        HeroNode node = root;
        while ( node != null && node.getLeftType() == 0 ) {
            node = node.getLeft();
        }
        while ( node != null ) {
            //右节点是线索
            if (node.getRightType() == 1) {
                System.out.print(node + ", ");
                pre = node;
                node = node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if (node.getRight() == pre) {
                    System.out.print(node + ", ");
                    if (node == root) {
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {    //如果从左节点的进入则找到有子树的最左节点
                    node = node.getRight();
                    while ( node != null && node.getLeftType() == 0 ) {
                        node = node.getLeft();
                    }
                }
            }
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
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//null
    private int leftType;//0-->左子树 1-->前驱节点

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    private HeroNode parent;//父节点的指针（为了后序线索化使用）

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    private int rightType;//0-->右子树 1-->前驱节点

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