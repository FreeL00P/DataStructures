package com.fj.tree;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/19 21:20    since 1.0.0
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr={4,6,8,5,9};
        heapSort(arr);
    }
    //堆排序
    public static void heapSort(int[] arr){
        int temp=0;
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //将堆顶元素与末尾元素交换 最大元素沉入数组末尾
        for (int i = arr.length-1; i >0 ; i--) {
            //交换
            temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
    }
    //将一个数组（二叉树），调整陈一个大顶堆
    /**       4
     *       /  \
     *      6    8
     *     / \
     *    5  9
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * 举例：int arr[]={4,6,8,5,9};=>i=1 adjustHeap=>得到{4,9,8,5,6}
     * 如果我们再次调用 adjustHeap 传入的i=0 => {4,9,8,5,6} =>{9,6,8,5,4}得到大顶堆
     * @param arr 待调整的数组
     * @param i    表示非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];//先取出当前元素的
        //开始调整
        //说明 ： k=i*2+1 k是i节点的左子节点
        for(int k=i*2+1;k<length;k=k*2+1){
            if (k+1<length&&arr[k]<arr[k+1]){//说明左子节点值小于右子节点值
                k++;//k指向右子节点
            }
            if (arr[k]>temp){//子节点大于父节点
                arr[i]=arr[k];//把较大值赋值给当前节点
                i=k;//i指向k，继续循环比较
            }else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶上（局部）
        arr[i]=temp;//将temp放入到调整后的位置
    }
}
