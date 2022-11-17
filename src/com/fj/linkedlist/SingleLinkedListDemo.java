package com.fj.linkedlist;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/5 16:14    since 1.0.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "刘娜", "及时雨");
        HeroNode heroNode6 = new HeroNode(6, "卢克", "玉麒麟");
        HeroNode heroNode7 = new HeroNode(7, "利亚", "智多星");
        HeroNode heroNode8 = new HeroNode(8, "蔡徐坤", "豹子头");
        //加入链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode5);
        singleLinkedList.add(heroNode7);

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(heroNode2);
        singleLinkedList2.add(heroNode4);
        singleLinkedList2.add(heroNode6);
        singleLinkedList2.add(heroNode8);
        HeroNode merge = SingleLinkedList.merge(singleLinkedList.head, singleLinkedList2.head);
        while (merge!=null){
            System.out.println(merge);
            merge=merge.next;

        }
//        singleLinkedList.addByOrder(heroNode1);
//        singleLinkedList.addByOrder(heroNode4);
//        singleLinkedList.addByOrder(heroNode2);
//        singleLinkedList.addByOrder(heroNode3);
//        singleLinkedList.list();
//        System.out.println();
//        //修改节点的代码
//        singleLinkedList.update(new HeroNode(2,"大卢","火麒麟"));
//        singleLinkedList.list();
//        System.out.println();
//
//        singleLinkedList.delete(4);
//        singleLinkedList.list();

//        singleLinkedList.reversList();
//        singleLinkedList.list();

    }
}

//定义heroNode 每个heroNode就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
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

//定义singleLinkedList管理hero
class SingleLinkedList{
    //初始化一个头节点
    public HeroNode head=new HeroNode(0,"","");

    //合并两个单向链表
    public static HeroNode merge(HeroNode head1, HeroNode head2){
        if (head1==null) return head2;
        if (head2==null) return head1;
        HeroNode dum=new HeroNode(0,"","");
        HeroNode cur=dum;
        while (head1!=null&&head2!=null){
            if (head1.no<=head2.no){
                cur.next=head1;
                head1=head1.next;
            }else {
                cur.next=head2;
                head2=head2.next;
            }
            cur=cur.next;//后移
        }
        //合并剩下链表
        cur.next=(head1!=null?head2:head1);
        return dum.next;
    }
    //添加到单向链表
    //1.找到当前链表的最后节点
    //2. 将最后节点的next指向新节点
    public void add(HeroNode heroNode){
        //头节点不能动 需要辅助节点进行遍历
        HeroNode temp=head;
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
    }
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
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
    //在添加英雄时 根据排名插入到指定位置
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动 因此我们仍然通过一个辅助变量来帮助我们找到添加的位置 、
        //因为是单链表，而且我们找的temp是位于添加位置的前一个节点 否则插入不了
        HeroNode temp=head;
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
            System.out.println("准备的插入的英雄的编号"+ heroNode.next.no+"已经存在");
        }else {
            //插入到链表中
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点的信息 根据no编号来进行修改
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("对象不存在");
            return;
        }
        HeroNode temp=head;
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
            return;
        }
        boolean flag=false;
        HeroNode temp=head;
        while (true){
            if (temp.next==null){//说明temp已经到了末尾 没有找到需要删除的节点
                break;
            }
            if (temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }

        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.println("没有找到需要修改的节点");
        }
    }

    //反转链表
    public void reversList(){
//
//        if (head.next==null||head.next.next==null) return;
//        //定义一个辅助变量 帮助我们遍历原链表
//        HeroNode cur=head.next;
//        HeroNode next=null;//指向当然节点的下一个节点
//        HeroNode reversHead=new HeroNode(0,"","");
//        while (cur!=null){
//            next=cur.next;//保存当前节点的下一个节点
//            cur.next=reversHead.next; //将cur的下一个节点移动到新链表的最前端
//            reversHead.next=cur;
//            cur=next;//后移
//        }
//        //将head.next指向reversHead.next
//        head.next=reversHead.next;
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reversNodeList = new HeroNode(0, " ", "");
        while (cur.next!=null){
            next=cur.next;
            cur.next=reversNodeList.next;
            reversNodeList.next=cur;
            cur=next;
        }
        head.next=reversNodeList.next;
    }
}