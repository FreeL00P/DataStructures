package com.fj.doublelinkedlist;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/6 21:59    since 1.0.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList .add(heroNode1);
//        doubleLinkedList .add(heroNode2);
//        doubleLinkedList .add(heroNode3);
//        doubleLinkedList .add(heroNode4);
        doubleLinkedList .addByOrder(heroNode4);
        doubleLinkedList .addByOrder(heroNode1);
        doubleLinkedList .addByOrder(heroNode2);
        doubleLinkedList .addByOrder(heroNode3);
        doubleLinkedList.list();
        doubleLinkedList.delete(1);
        System.out.println();
        doubleLinkedList.list();
        HeroNode2 newHeroNode = new HeroNode2(2, "蔡徐坤", "豹子头");
        doubleLinkedList.update(newHeroNode);
        System.out.println();
        doubleLinkedList.list();
    }
}
//定义heroNode 每个heroNode就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
class  DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");
    //显示链表所有元素
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        //遍历链表
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp=temp.next;
        }
    }
    //添加节点到双向链表
    public void add(HeroNode2 heroNode){
        //1.找到当前链表的最后节点
        //2. 将最后节点的next指向新节点
        //3.新节点pre指向前一个节点
        //头节点不能动 需要辅助节点进行遍历
        HeroNode2 temp=head;
        //遍历链表 找到最后节点
        while (true){
            if (temp.next==null){
                break;
            }
            //将temp后移
            temp=temp.next;
        }
        //当退出while循环时 temp到链表最后
        temp.next=heroNode;
        heroNode.pre=temp;
    }
    //修改节点的信息 根据no编号来进行修改
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("对象不存在");
            return;
        }
        HeroNode2 temp=head;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no==heroNode.no){//说明找到了我们需要修改的节点
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=heroNode.name;
            temp.nickname=heroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点",heroNode.no);
        }
    }
    //删除节点
    public void delete(int no){
        //判断链表是否为空
        if (head==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag=false;//标志是否找到待删除的节点
        HeroNode2 temp=head.next;
        while (true){
            if (temp==null){//说明temp已经到了末尾 没有找到需要删除的节点
                System.out.println();
                break;
            }
            if (temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next=temp.next;
            if (temp.next!=null) //如果是最后一个节点就不需要执行下面这句话 否则出现空指针异常
                temp.next.pre=temp.pre;
        }else {
            System.out.println("没有找到需要修改的节点");
        }
    }
    //在添加英雄时 根据排名插入到指定位置
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动 因此我们仍然通过一个辅助变量来帮助我们找到添加的位置 、
        HeroNode2 temp=head;
        boolean flag=false;//标识添加的编号是否存在
        //遍历链表
        while (true){
            if (temp.next==null) break;//说明temp已经在链表最后
            if (temp.next.no>heroNode.no){//位置找到就在temp后面插入
                break;
            }else if (temp.next.no==heroNode.no){//说明添加的hero已经存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){//不能添加 说明编号存在
            System.out.println("准备的插入的英雄的编号"+ heroNode.no+"已经存在");
        }else {
            //插入到链表中
            heroNode.next=temp.next;
            if (temp.next!=null)
                temp.next.pre=heroNode;
            temp.next=heroNode;
            heroNode.pre=temp;
        }
    }
}