package com.fj.sort;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/11 20:29    since 1.0.0
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int [] arr=new int[80000];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void quickSort(int[] arr,int left,int right){
        int l=left;//左下标
        int r=right;//右下标
        int pivot=arr[(left+right)/2];//基准值
        int temp=0;
        //循环的目的是把pivot小的值放在左边 大的值放在右边
        while (l<r){//
            while (arr[l]<pivot){//在pivot左-->右移动 直到arr[l]大于pivot 此时
                l+=1;
            }
            while (arr[r]>pivot){//在pivot右-->左移动 直到arr[l]小于于pivot 此时
                r-=1;
            }
            if (l>=r){
                //在上面两个while循环 l r在不停往pivot移动
                // 当两者重合时 两边的值都满足需求 pivot小的值放在左边 大的值放在右边
                break;
            }
            //因为我们要求把小于pivot的放在左边 大于放在右边
            //所以我们需要交换上面得到的 l r 对应的值进行交换
            // 来满足于需求
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            //如果交换完后发现arr[l]==pivot   --> r前移
            if (arr[l]==pivot){
                r-=1;
            }
            //如果交换完后发现arr[r]==pivot   --> l后移
            if (arr[l]==pivot){
                l+=1;
                r-=1;
            }
        }
        //如果 l==r 必须l++;r--;否则会出现栈溢出
        if (l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if (left<r){
            quickSort(arr,left,r);
        }
        if (right>l){
            quickSort(arr,l,right);
        }
    }
}
