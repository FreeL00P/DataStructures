package com.fj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/8 21:46    since 1.0.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(3+4)*5-6==> 3 4 + 5 * 6 -
        String suffixExp="1+((2+3)*4)-5";
        List<String> infixExp= toInfixExp(suffixExp);
        System.out.println("中缀表达式List："+infixExp);
        List<String> parseSuffixExpList = parseSuffixExpList(infixExp);
        System.out.println("后缀表达式List："+parseSuffixExpList);
        System.out.println("结果"+cal(parseSuffixExpList));
        //先将 3 4 + 5 * 6 -放入ArrayList
        //将ArrayList传递给一个方法 遍历ArrayList配合栈完成计算
/*        List<String> listString = PolandNotation.getListString(suffixExp);
        System.out.println(listString);
        int res=cal(listString);
        System.out.println(res);*/
        //
    }
    //将一个逆波兰表达式放入ArrayList
    public static List<String> getListString(String suffixExp){
        //将suffixExp分割
        String[] split=suffixExp.split(" ");
        List<String> list=new ArrayList<String>();
        //将分割后的结果放入ArrayList
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }
    //计算
    public static int cal(List<String> list){
        //创建栈
        Stack<String> stack= new Stack<String>();
        //遍历list
        for (String item:list) {
            //使用正则表达式来取数
            if (item.matches("\\d+")){//匹配多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数并运算 在入栈
                int num2= Integer.parseInt(stack.pop());
                int num1= Integer.parseInt(stack.pop());
                int res=0;
                if (item.equals("+")){
                    res=num1+num2;
                }else if (item.equals("-")){
                    res=num1-num2;
                }else if (item.equals("*")){
                    res =num1*num2;
                }else if (item.equals("/")){
                    res=num1/num2;
                }else {
                    throw  new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
    //将中缀表达式转换成对应list
    public static List<String> toInfixExp(String s){
        //定义一个list存放中缀表达式对应的内容
        List<String> ls=new ArrayList<String>();
        int i=0;//用于遍历中缀表达式
        String str;
        char c;//每遍历一次字符就放入到c中
        do{
            //如果c不是一个数字 加入到ls
            if ((c=s.charAt(i))< 48|| (c=s.charAt(i))> 57){
                ls.add(""+c);
                i++;
            }else {
                //如果是一个数 需要考虑多位数问题
                str="";//置空
                while (i<s.length()&&((c=s.charAt(i))>=48&& (c=s.charAt(i))<= 57)){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }
    //将中缀表达式转换成对应list 转换为后缀表达式的list
    public static List<String> parseSuffixExpList(List<String> list){
        //定义两个栈
        Stack<String>  s1=new Stack<String>();
        //因为s2栈整个转换过程中没有pop()操作 而且我们后面，还需要逆序输出
        //因此比较麻烦 这里 我们就不用Stack 而直接使用List
        List<String>  s2=new ArrayList<>();
        //遍历list
        for (String item:list){
            //如果是一个数 入数栈
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                //如果是一个左括号 直接入符号栈
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号 则依次弹出s1栈顶的运算符并压入s2
                // 直到遇到左括号为止 此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出s1栈
            }else {
                //当item的优先级小于等于s1栈顶运算符的优先级，将s1栈顶的运算符弹出 加入入s2中
                while (s1.size()!=0&&operation.getValue(s1.peek())>=operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item加入栈
                s1.push(item);
            }
        }
        //把s1剩余的运算符压入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }
}
//编写一个类 Operation 返回一个运算符的优先级
class operation{
    public static int ADD=1;
    public static int SUB=1;
    public static int MUL=2;
    public static int Div=3;
    public static int getValue(String oper){
        int result=0;
        switch (oper){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=Div;
                break;
        }
        return result;
    }
}