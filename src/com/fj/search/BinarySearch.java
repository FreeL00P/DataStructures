package com.fj.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/13 14:29    since 1.0.0
 * 二分查找
 * 必须为有序数组才能使用
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={1,3,66,66,66,89,100};
        List<Integer> list = binarySearch(array, 0, array.length, 100);
        if (list.size()!=0){
            System.out.println(list);
        }


    }
    //二分查找
    public static List<Integer> binarySearch(int[] array, int left, int right, int findVal){
        System.out.println("查找次数");
        //当left >right时 说明递归了整个数组 但没有找到
        if (left>right) return  new ArrayList<Integer>();
        int mid=(left+right)/2;
        int midVal=array[mid];
        if (findVal>midVal){
            return binarySearch(array,mid+1,right,findVal);
        }
        if (findVal<midVal){
            return binarySearch(array,left,mid-1,findVal);
        }else {
            //将查找到的值放入ArrayList
            List<Integer> list = new ArrayList<Integer>();
            int temp=mid-1;//查找到的下标的左边第一个下标
            while (true){
                if (temp<0||array[temp]!=findVal){//找到末尾或左边第一个元素不等于找到的元素
                    break;
                }
                list.add(temp);
                temp-=1;
            }
            list.add(mid);
            //向右扫描
            temp=mid+1;//右边第一个下标
            while (true){
                if (temp>array.length-1||array[temp]!=findVal){//找到末尾或左边第一个元素不等于找到的元素
                    break;
                }
                list.add(temp);
                temp+=1;
            }
            return list;
        }


    }
}
