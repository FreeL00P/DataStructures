package com.fj.linkedlist;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/7 13:39    since 1.0.0
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.count(1,2,5);
    }
}
//创建一个环形单向链表
class CircleSingleLinkedList{
    //创建一个first节点
    Boy first=new Boy(-1);
    //添加小孩节点 构建成环形链表
    public void add(int num){
        if (num<1){
            System.out.println("num数据有误");
            return;
        }
        Boy curBoy=null;//辅助变量 帮助我们构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= num; i++) {
            //根据编号创建小孩节点
            Boy boy=new Boy(i);
            if (i==1){//构建第一个小孩
                first=boy;
                first.setNext(first);//构成环
                curBoy=first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }
    //遍历环形链表
    public void list(){
        //判断链表是否为空
        if (first==null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext()==first){//已经到链表最后
                break;
            }
            curBoy=curBoy.getNext();//后移
        }
    }
    //根据用户输入计算出出圈顺序

    /**
     *
     * @param startNo 从第一个开始数数
     * @param countNum 表示数几下
     * @param num 表示最初有多少小孩在圈中
     */
    public void count(int startNo,int countNum,int num){
        //数据校验
        if (first==null||startNo<1||startNo>num){
            System.out.println("参数有误");
            return;
        }
        //创建一个辅助指针 帮助完成小孩出圈
        Boy helper=first;
        //while循环 将helper指向最后一个节点
        while (true){
            if (helper.getNext()==first){//说明helper指向最后节点
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前 先让first和helper移动k-1次
        for (int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时 让first和helper同时移动m-1次 然后出圈
        //这里是循环操作 直到圈中只有一个节点
        while (true){
            if (helper== first){//说明圈中只有一个节点
                break;
            }
            //让first和helper同时移动m-1
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时 first指向的节点就是需要出圈的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext();//
            helper.setNext(first);
        }
        System.out.printf("\n圈中最后的小孩%d",first.getNo());
    }
}



class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
