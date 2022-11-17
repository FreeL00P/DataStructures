package com.fj.recursion;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/9 21:54    since 1.0.0
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组 模拟迷宫
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下为1
        for (int i = 0; i <7 ; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        //使用递归回溯
        boolean b = setWay(map, 1, 1);
        System.out.println("小球");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    //使用递归回溯给小球找路/
    // 如果小球找到[6][5]这个位置 表示找到
    //规定 map[i][j]为0时表示没有走过 1表示墙 2 表示通路可以走 3表示走过 但走不通
    //策略 下-->右-->上-->左 如果该点走不通 回溯
    /**
     *
     * @param map 表示地图
     * @param i 表示从哪个位置开始找
     * @param j
     * @return 找到路返回true
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){//说明通路已经找到
            return true;
        }else {
            if (map[i][j]==0){
                //按照策略走
                map[i][j]=2;//假定改点是可以走通的
                if (setWay(map,i+1,j)){//向下走
                    return true;
                }else if (setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)) {//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点走不通
                    map[i][j]=3;
                    return false;
                }
            }else {//如果map[i][j]!=0 --> 1 2 3
                return false;
            }
        }

    }
}
