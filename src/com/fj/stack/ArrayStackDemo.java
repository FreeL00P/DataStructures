package com.fj.stack;


import java.util.Scanner;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/8 13:28    since 1.0.0
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);

/*        LinkedStack linkedStack=new LinkedStack(5);
       man man1= new man(1);
        man man2= new man(2);
        man man3= new man(3);
        man man4= new man(4);
        man man5= new man(5);
        linkedStack.push(man1);
        linkedStack.push(man2);
        linkedStack.push(man3);
        linkedStack.push(man4);
        linkedStack.push(man5);
        linkedStack.list();
        System.out.println("出栈一个数据");
        linkedStack.pop();
        linkedStack.list();*/

        String key="";
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show：显示栈");
            System.out.println("exit：退出栈");
            System.out.println("push：添加数据");
            System.out.println("pop: 取出数据");
            System.out.println("输入你的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("输入一个数字");
                    int value=scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.println("出栈的数据是"+pop);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:break;
            }
        }

    }
}
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top=-1;//表示栈顶
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        //判断栈是否满
        if (isFull()) return;
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        //将栈顶的数据返回
        if (isEmpty()) throw new RuntimeException("栈空");
        int value=stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}

class LinkedStack{
    private int maxSize;
    private int size;
    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    private man head=new man(0);
    //栈满
    public boolean isFull(){
        return size>=maxSize;
    }
    //栈空
    public boolean isEmpty(){
        return head.next==null;
    }
    //入栈
    public void push(man man){
        //判断栈是否满
        if (isFull()){ System.out.println("满"); return;}
        man.next=head.next;
        head.next=man;
        size++;
    }
    //出栈
    public man pop(){
        //将栈顶的数据返回
        if (isEmpty()) throw new RuntimeException("栈空");
        man value=head.next;
        head.next=head.next.next;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        man temp=head.next;
        while (true){
            if (temp==null)break;
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
class man{
    public int no;
    public man next;
    public man(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "man{" +
                "no=" + no +
                '}';
    }
}