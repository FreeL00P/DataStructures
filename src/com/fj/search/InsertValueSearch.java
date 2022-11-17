package com.fj.search;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/13 15:37    since 1.0.0
 * 插值查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int [] arr=new int[100];
        for (int i=0;i<100;i++){
            arr[i]=i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);

    }
    //编写插值查找
    public static int insertValueSearch(int[] array,int left,int right,int findVal){
        System.out.println("查找次数");
        //扫描完毕 小于最小值 大于最大值
        if (left>right||findVal<array[0]||findVal>array[array.length-1]) return -1;
        int mid=left + (right - left)*(findVal - array[left])/(array[right] - array[left]);
        int midVal=array[mid];
        if (findVal>midVal){
            return insertValueSearch(array,mid+1,right,findVal);
        }
        if (findVal<midVal){
            return insertValueSearch(array,left,mid-1,findVal);
        }else {
            return mid;
        }

    }
}
