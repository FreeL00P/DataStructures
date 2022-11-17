package com.fj.search;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/13 20:57    since 1.0.0
 * 斐波那契查找
 */
public class FibonacciSearch {
    public static int maxSize=10;
    public static void main(String[] args) {
        int[] arr={1,8,10,89,1000,1234};
        int i = fibSearch(arr, 10000);
        System.out.println(i);
    }
    //得到一个斐波那契数列
    public static int[] fibonacci(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < maxSize; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
    //斐波那契查找算法

    /**
     *
     * @param a 数组
     * @param key 查找的值
     * @return 返回对应的下标
     */
    public static int fibSearch(int[] a,int key){
        int low=0;
        int high=a.length-1;
        int k=0;//斐波那契数列分割数值的下标
        int mid=0;
        int[] f=fibonacci();//获取斐波那契数列
        //获取斐波那契数列分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //因为f[k]值可能大于数组a的长度
        //使用Arrays类构造一个新数组
        int[] temp= Arrays.copyOf(a,f[k]);
        //使用a数组最后的数填充temp
        for (int i = high+1; i <temp.length ; i++) {
            temp[i]=a[high];
        }
        //使用while循环找到key
        while (low<=high){
            mid=low+f[k-1]-1;
            if (key<temp[mid]){//说明key在mid左边
                high=mid-1;
                k--;//在f[k-1]的前面继续查找 即下次循环的mid=f[k-1-1]-1
            }else if (key>temp[mid]){//说明key在mid右边
                low=mid+1;
                //为什么-2
                //全部元素=前面的元素+后面的元素
                // f[k]=f[k-1]+f[k-2]
                //因为后面我们有f[k-2] 所以可以继续拆分 f[k-1]=f[k-3]+f[k-4]
                //即在f[k-2]的前面查找 k-=2;
                //即下次循环的mid=f[k-1-2] -1
                k-=2;
            }else {//找到
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
