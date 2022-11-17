package com.fj.sort;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/11 14:32    since 1.0.0
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int [] arr=new int[8000000];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        shellSort2(arr);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        int[] nums={170,-368,148,672,397,-629,-788,192,170,309,-615,-237};
        shellSort(nums);
        System.out.println(Arrays.toString(nums));

    }
    //交换法
    public static void shellSort(int[] array){
        int temp=0;
        int count=0;
        for (int gap= array.length/2;gap>0;gap/=2){//考虑怎么分组
            //将10个数据分成5组 10/2
            for (int i = gap; i<array.length ; i++) {//对组进行遍历
                //遍历各组中所有的元素 步长为gap ij--> 05 16 27 38 49
                for (int j = i-gap; j >=0 ; j-=gap) {//对每个组元素进行遍历
                    //如果当前元素大于加上步长后的元素 说明交换
                    if (array[j]>array[j+gap]){
                        temp=array[j];
                        array[j]=array[j+gap];
                        array[j+gap]=temp;
                    }else {
                        break;
                    }
                }
            }
            //System.out.printf("希尔排序第%d轮的情况%s\n",++count,Arrays.toString(array));
        }

    }
    //移位法
    public static void shellSort2(int[] array){
        for (int gap= array.length/2;gap>0;gap/=2){
            //gap个元素开始 逐个对其所在的组进行直接插入
            for (int i = gap; i <array.length ; i++) {
                int j=i;
                int temp=array[j];
                if (array[j]<array[j-gap]){//j 和前面的元素比较
                    while (j-gap>=0&&temp<array[j-gap]){
                        //移动
                        array[j]=array[j-gap];
                        j-=gap;
                    }
                    //当退出while循环后 就给temp找到了插入的位置
                    array[j]=temp;
                }

            }
        }
    }
}
