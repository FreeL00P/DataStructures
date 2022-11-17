package com.fj.sort;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/10 21:10    since 1.0.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array={3,9,-1,10,4,50};
        int[] sortsArray = bubbleSort(array);
        System.out.println("排序好的结果-->");
        System.out.println(Arrays.toString(sortsArray));
    }
    public static int[] bubbleSort(int[] array){
        for (int i = 0; i <array.length ; i++) {
            int temp;
            boolean flag=false;
            for (int j = 0; j < array.length-i-1; j++) {
                if (array[j]>array[j+1]){
                    //如果进行了位置交换 把flag-->true
                    flag=true;
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            if (!flag){//说明在第i次循环的时候没有进入到交换语句说明已经满足排序要求
                return array;
            }
            System.out.printf("第%d次排序：-->",i+1);
            System.out.print(Arrays.toString(array)+"\n");
        }
        return array;
    }
}
