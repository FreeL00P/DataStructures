package com.fj.sparsearray;

import java.io.*;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/4 20:08    since 1.0.0
 *
 */
public class SparseArray {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建一个原始的二维数组11*11
        // 0: 表示没有棋子 1 表示黑 2 表示蓝
        int chessArr[][] =new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][4]=2;
        System.out.println("原始的二维数组");
        for (int[] ints : chessArr) {
            for (int data: ints) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //转换为稀疏数组
        //遍历元二维数组 得到有效数据
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11 ; j++) {
                if (chessArr[i][j]!=0){
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组 将非 0 值存放到稀疏数组
        int count=0;//用于记录为第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11 ; j++) {
                if (chessArr[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }
        //序列化对象到本地
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\map.data"));
        objectOutputStream.writeObject(sparseArr);
        //遍历稀疏数组
        System.out.println("稀疏数组");
        for (int i = 0; i <sparseArr.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        //将稀疏数组转换为普通数组
        int row=sparseArr[0][0];
        int col=sparseArr[0][1];
        int val=sparseArr[0][2];
        int[][] chessArr2 = new int[row][col];
        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println("转换为新二维数组");
        for (int[] ints : chessArr2) {
            for (int data: ints) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\map.data"));
        int[][] object = (int[][]) objectInputStream.readObject();

    }
}
