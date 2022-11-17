package com.fj.stack;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/8 16:44    since 1.0.0
 */
public class Calculator {
    public static void main(String[] args) {
        String exp="30+2*6-2";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index=0;//用于扫描
        int num1=0;
        int num2;
        int oper=0;
        int res=0;
        char ch=' ';//保存每次扫描的结果
        String keepNum="";
        //循环扫描exp
        while (true){
            ch=exp.substring(index,index+1).charAt(0);
            //判断ch是数字或符号
            if (operStack.isOper(ch)){//如果是不是运算符
                if (!operStack.isEmpty()){//如果符号栈中有操作符
                    //如果当前操作符的优先级小于或等于栈中的操作符 就需要在数栈中取出两个数
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        //把当前的操作符入符号栈
                        operStack.push(ch);
                    }else {//如果当前符号优先级大于栈中的优先级
                        operStack.push(ch);
                    }
                }else {//如果为空
                    operStack.push(ch);
                }
            }else {//如果是数 直接入数栈
                //在处理多位数时 不能发现是一个数就入栈
                //应该要继续扫描下一位数 直到下一个ch不是数
                //keepNum字符串变量用于拼接数
                keepNum+=ch;
                //如果ch一已经是exp的最后一位 则直接入栈
                if (exp.length()-1==index){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字 如果是就继续扫描
                    if (operStack.isOper(exp.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";//清空
                    }
                    numStack.push(ch-48);//'1'-->1
                }
            }
            index++;//判断是否扫描到exp最后
            if (index>=exp.length()){
                break;
            }
        }
        //当表达式扫描完毕后 从数栈和pop出相应的数和符号
        while(true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈最后的数pop出 就是结果
        System.out.printf("表达式%s=%d",exp,numStack.pop());
    }
}


class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top=-1;//表示栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        //判断栈是否满
        if (isFull()) return;
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        //将栈顶的数据返回
        if (isEmpty()) throw new RuntimeException("栈空");
        int value=stack[top];
        top--;
        return value;
    }
    //返回当前栈顶的值
    public int peek(){
        return stack[top];
    }
    //遍历栈
    public void list(){
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //返回运算符的优先级
    public int priority(int oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper ){
        int res=0;//结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//注意顺序
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num1/num2;
                break;
            default:break;
        }
        return res;
    }
}

