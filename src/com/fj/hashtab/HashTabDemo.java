package com.fj.hashtab;

import java.util.Scanner;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/14 15:10    since 1.0.0
 */
public class HashTabDemo  {
    public static void main(String[] args) {
        //创建hash表
        hashTab hashTab = new hashTab(7);
        String key="";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add-->添加雇员");
            System.out.println("list-->显示雇员");
            System.out.println("find-->查找雇员");
            System.out.println("exit-->退出系统");
            key=scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();
                    System.out.println("输入名字");
                    String name=scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入需要查找的id");
                    id=scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    System.out.println("退出");
                    break;
                default:
                    break;
            }
        }
    }
}
//创建hashTab
class hashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public hashTab(int size) {
        this.size=size;
        //初始化 empLinkedListArray
        empLinkedListArray=new EmpLinkedList[size];
        for (int i = 0; i <empLinkedListArray.length ; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    public void add(Emp emp){
        //根据员工的id，得到该员工的id决定插入到哪条链表
        int empLinkedListNo=hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //遍历所有链表
    public void list(){
        for (int i = 0; i <empLinkedListArray.length ; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //根据输入的id查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp!=null){
            System.out.printf("在第%d条链表找到雇员-->id=%d name=%s\n",empLinkedListNo,emp.id,emp.name);
        }else {
            System.out.println("没有找到");
        }

    }
    //编写散列函数 使用一个简单取模法
    public int hashFun(int id){
        return id%size;
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp  next; //默认为空
    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
//表示链表
class  EmpLinkedList{
    private Emp head;
    //添加雇员到链表
    public void add(Emp emp){
        if (head==null){//如果是添加第一个
            head=emp;
            return;
        }
        //表示第一个
        Emp cur=head;
        while (true){
            if (cur.next==null){
                break;
            }
            cur=cur.next;
        }
        //退出时直接将emp加入链表最后
        cur.next=emp;
    }
    //遍历链表
    public void list(int no){
        if (head==null){
            System.out.println("第"+no+"条链表为NULL");
            return;
        }
        System.out.print("第"+no+"条链表INFO：");
        Emp cur=head;
        while (true){
            System.out.printf("-->id=%d name=%s \t ",cur.id,cur.name);
            if (cur.next==null){
                break;
            }
            cur=cur.next;
        }
        System.out.println();
    }
    //根据id查找雇员
    public Emp findEmpById(int id){
        if (head==null){
            System.out.println("链表为NULL");
            return null;
        }
        Emp cur=head;
        while (true){
            if (cur.id==id){
                break;
            }
            if (cur.next==null){
                cur=null;
                break;
            }
            cur=cur.next;
        }
        return cur;
    }

}