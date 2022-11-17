package com.fj.queue;

import java.util.Scanner;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/4 21:00    since 1.0.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while (loop){
            System.out.println("s 显示队列");
            System.out.println("e 退出程序");
            System.out.println("a 添加数据");
            System.out.println("g 取出数据");
            System.out.println("h 查看队头");
            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int n=scanner.nextInt();
                    arrayQueue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出的数据是"+queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = arrayQueue.headQueue();
                        System.out.println("队列头的数据"+headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;

            };
        }
    }
}
class ArrayQueue{
    private int maxSize;//array maxSize
    private int front;//队列头部
    private int rear;//尾部
    private int[] arr;//模拟队列
    //创建队列构造器

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
        front=-1;//指向队列头部的前一个位置
        rear=-1;//直接指向队列尾部
    }
    //判断队列是否满
    public boolean isFill(){
        return rear==maxSize-1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //添加数据到队列
    public void addQueue(int n){
        if (isFill()){
            System.out.println("队列满");
            return;
        }
        rear++;//让rear后移
        arr[rear]=n;
    }

    //出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }
    //显示队列数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}