package com.fj.sort;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/12 14:39    since 1.0.0
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        //int arr[]={8,4,5,7,1,4,6,2};
        int [] arr=new int[80000];
        int temp[]=new  int[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
    //分
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid=(left+right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     *
     * @param arr 排序的原数组
     * @param left 左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //System.out.println("合并");
        int i=left;//左边有序序列的初始索引
        int j=mid+1;//右边有序序列的初始索引
        int t=0;//指向temp数组的当前索引
        //先把左右两边（有序）的数据 按照规则填充到temp数组
        //直到有一边处理完毕
        while (i<=mid&&j<=right){
            if (arr[i]<arr[j]){//如果左边序列的当前元素小于右边的当然元素
                temp[t]=arr[i];//将左边当前元素拷贝到temp
                t++;
                i++;
            }else {//反之 右边小于左边
                temp[t]=arr[j];//将右边当前元素拷贝到temp
                t++;
                j++;
            }
        }
        //把有剩余数据的一边填充到temp
        while (i<=mid){//说明左边数据还有剩余数据
            temp[t]=arr[i];
            t++;
            i++;
        }
        while (j<=right){//说明左边数据还有剩余数据
            temp[t]=arr[j];
            t++;
            j++;
        }
        //将temp数组的元素拷贝到arr
        t=0;
        int tempLeft=left;
        //System.out.println("tempLeft"+tempLeft+"right"+right);
        while (tempLeft<=right){
            arr[tempLeft]=temp[t];
            t++;
            tempLeft++;
        }
    }
}
