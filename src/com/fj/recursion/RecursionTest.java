package com.fj.recursion;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/9 21:15    since 1.0.0
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(10);
    }
    public static void test(int n){
        if (n>1){
            test(n-1);
        }
        System.out.println("n="+n);
    }
}
