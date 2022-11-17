package com.fj.sort;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/10 21:44    since 1.0.0
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] array={2,4,1,5,7,9,10,6};
        int [] arr=new int[80000];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        //System.out.println("排序后的数组-->"+Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static int[] selectSort(int[] array){
        for (int i = 0; i <array.length-1; i++) {
            //每轮默认认为当前arr[i]是最小值
            int min=array[i];
            int index=i;//其对应的下标
            for (int j =i+1; j <array.length; j++) {
                if (min>array[j]){//如果array[j]后面有比min更小的对应数据 则将array[j]对应的值赋值给min j赋值给index
                    min =array[j];
                    index=j;
                }
            }
            //进行把最小值和array[i]位置交换
            //最小值下标对应的数据与 arr[i]交换位置
            if (index!=i){//index的值没有改变 即我们假定的array[i]就是最小值 不需要进行交换
                array[index]=array[i];
                array[i]=min;
            }
            //System.out.printf("第%d轮选择排序，min-->%d，排序的结果为-->%s\n",i+1,min, Arrays.toString(array));
        }
        return array;
    }
}
