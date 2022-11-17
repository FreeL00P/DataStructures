package com.fj.sort;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/12 20:59    since 1.0.0
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int [] arr=new int[80000];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void radixSort(int[] array){
        //得到数组中最大数的位数
        int max=array[0];
        //得到最大数
        for (int i = 0; i < array.length; i++) {
            if (array[i]>max) max=array[i];
        }
        int maxLength=(max+"").length();
        //对每个元素的个位进行处理
        //定义一个二维数组 表示10个桶 每个桶就是一个一维数组
        int[][] bucket=new int[10][array.length];//为了防止数据溢出 每个桶大小为数组的长度
        //为了知晓每个桶中实际存放了多少数据 我们定义个一维数组来记录各个桶中每次存放的数据数
        int[] bucketElementCounts=new int[10];
        for (int k = 0,n=1; k < maxLength; k++,n*=10) {
            for (int i = 0; i <array.length ; i++) {
                int digitOfElement=array[i]/n%10;//取出对应位 第一次个位 2十位 3白薇。。。
                //放入相应的桶中
                //digitOfElement 对应的桶的下标  bucketElementCounts[digitOfElement]-->对应桶中数据的存放位置
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[i];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序，一维数组的下标依次取出数据， 放入原先的数组。
            int index=0;
            //遍历每一个桶，并将桶中数据放入原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                // 如果桶中有数据 才放入到原数组
                if (bucketElementCounts[i]!=0){
                    //循环该桶 即第i个桶
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        //取出元素放入原数组
                        array[index++]=bucket[i][j];
                    }
                }
                //第i轮处理后 bucketElementCounts[i]=0
                bucketElementCounts[i]=0;
            }
            //System.out.printf("第%d轮排序处理%s\n",(k+1),Arrays.toString(array));
        }

    }
}
