package com.fj.queue;

import java.util.Scanner;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/5 14:19    since 1.0.0
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray circleArray = new CircleArray(5);
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
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int n=scanner.nextInt();
                    circleArray.addQueue(n);
                    break;
                case 'g':
                    try {
                        int queue = circleArray.getQueue();
                        System.out.println("取出的数据是"+queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = circleArray.headQueue();
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
class CircleArray{
    private int maxSize;//array maxSize
    //front含义做调整 front指向队列第一个元素
    private int front;
    //real含义做调整 real指向队列最后一个元素的后一个原素 默认值为0
    private int rear;//尾部
    private int[] arr;//模拟队列
    //创建队列构造器

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }
    //判断队列是否满
    public boolean isFill(){

        return (rear+1)%maxSize==front;
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
        //因为real直接指向后一个元素 直接添加
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }

    //出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //这里需要分析出 front指向队列的第一个元素
        // 1. 先把front对应值保存到一个临时变量
        // 2. 将front后移 考虑取模
        // 3. 将临时变量的值返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //获取当前队列有效数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}