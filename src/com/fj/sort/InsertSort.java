package com.fj.sort;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/11 12:37    since 1.0.0
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int [] arr=new int[80000];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void insertSort(int[] array){
        //插入排序
        //将一个数组看成一个有序数组和无序数组 [(1,2),4,3,5]
        //从无序数组中选出一个数与有序数组中的每一个比较 确定插入位置
        for (int i = 1; i <array.length ; i++) {
            int insertVal=array[i];
            int insertIndex=i-1;//array[i]的前一个元素
            while (insertIndex>=0&&insertVal<array[insertIndex]){
                array[insertIndex+1]=array[insertIndex];//array[insertIndex]后移
                insertIndex--;
            }
            //当退出while循环时 说明插入的位置找到 insertIndex+1
            //判断是否需要赋值
            if (insertVal+1!=i){
                array[insertIndex+1]=insertVal;
            }
            //System.out.printf("第%d轮排序后的结果%s\n",i, Arrays.toString(array));
        }
    }
}
