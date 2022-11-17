package com.fj.linkedlist;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/5 21:58    since 1.0.0
 */
public class Test01 {
    public static void main(String[] args) {
        HeroList heroList = new HeroList();
        Hero hero1 = new Hero(1, "1");
        Hero hero2 = new Hero(2, "2");
        Hero hero3 = new Hero(3, "3");
        Hero hero4= new Hero(4, "4");
        Hero hero5 = new Hero(5, "5");
        Hero hero6 = new Hero(6, "6");
        heroList.add(hero1);
        heroList.add(hero2);
        heroList.add(hero3);
        heroList.add(hero4);
        heroList.add(hero5);
        heroList.add(hero6);
//        int i = heroList.sum_();
//        System.out.println(i);
//        Hero lastIndex = heroList.findLastIndex(6);
//        System.out.println(lastIndex);

        heroList.reverse();
    }
}
class Hero{
    public int no;
    public String name;
    public Hero  next;

    public Hero(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
class HeroList{
    Hero head=new Hero(0,"");
    public void add(Hero hero){
        Hero temp=head;
        while (true){
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=hero;
    }
    //求单链表节点个数
    public int sum_(){
        Hero temp=head;
        int count=0;
        if (head.next==null){
            return 0;
        }
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
            count++;
        }
        return count;
    }
    //查找单链表的倒数第k个节点
    public Hero findLastIndex(int k){
        if (head.next==null){
            return null;
        }
        Hero temp=head;
        int linkSize=sum_();//获取链表节点总数
        int num=(linkSize-k)+1;
        int i=0;
        if (k<=0||k>linkSize)
            return null;
        while (i<num){
            temp=temp.next;
            i++;
        }
        return temp;
    }
    //反转
    public void reverse(){
        HeroList heroList2 = new HeroList();
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        Hero temp=head.next;
        //遍历链表
        while (true){
            if (temp==null){
                break;
            }
            heroList2.add(temp);

            //将temp后移
            temp=temp.next;
        }

    }
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        Hero temp=head.next;
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
}