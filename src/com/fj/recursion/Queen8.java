package com.fj.recursion;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/9 23:01    since 1.0.0
 */
public class Queen8 {
    //定义一个max表示有多少皇后
    int max=8;
    //定义数组array 保存皇后的位置结果
    int[] array=new int[max];
    static int count=0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法",count);
    }
    //编写方法 放置第n个皇后
    //每一次递归时 进入到check()中都有for循环 因此会有回溯
    private void check(int n){
        if (n==max){//说明已经放置好了最后一个皇后，当前放置的是第九个 直接
            print();
            return;
        }
        //依次放入皇后
        for (int i = 0; i <max ; i++) {
            //先把当前皇后 n 放入该行第一列
            array[n]=i;
            //判断当放第n个皇后到i列时 是否冲突
            if (judge(n)){//不冲突
                //接着放 n+1个皇后开始递归
                check(n+1);
            }
            //如果冲突 就继续执行 array[n]=i;
            //即将array[n]=i放入本行后移的一个位置
        }
    }
    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     * @param n 第n个皇后
     * @return boolean
     */
    private boolean judge(int n){
        for (int i = 0; i < n ; i++) {
            //判断array[i]==array[n] 判断皇后是否和前面n-1个皇后在同一列
            //Math.abs(n-1)==Math.abs(array[n]-array[i]) 表示第n个皇后是否和第i个皇后在同一斜线
            if (array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    //将皇后摆放的位置打印出来
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
