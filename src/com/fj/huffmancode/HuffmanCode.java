package com.fj.huffmancode;

import java.util.*;

/**
 * Copyright (C), 2017-2022
 * <author>          <time>              <version>
 * 冯俊        2022/7/21 21:24    since 1.0.0
 * 哈夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content="hello world";
        byte[] contentBytes = content.getBytes();
        System.out.println("压缩前的结果是:"+Arrays.toString(contentBytes));
        byte[] huffmanCodesBytes=huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:"+Arrays.toString(huffmanCodesBytes));
        byte[] decode = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("解压后的结果:"+new String(decode));

    }
    //编写一个方法完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffManBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffManBytes){
        //先得到huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <huffManBytes.length ; i++) {
            boolean flag = (i == huffManBytes.length - 1 && huffManBytes[i] > 0);;//判断是否是最后一个字节
            stringBuilder.append(byteToBitString(!flag,huffManBytes[i]));
        }
        System.out.println("解压后的字符串"+stringBuilder.toString());
        //把字符串按照指定和赫夫曼编码进行解码
        //把赫夫曼编码进行调换,反向查询 100->a
        Map<String,Byte> map=new HashMap<String,Byte>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合存放bytes
        List<Byte> list = new ArrayList<Byte>();
        for(int i=0;i<stringBuilder.length();){
            int count=1;//计数器
            boolean flag=true;
            Byte b=null;
            //
            while (flag){
                String key=stringBuilder.substring(i,i+count);//i不动 让count移动 直到匹配
                b=map.get(key);
                if(b==null){//没有匹配到
                    count++;
                }else {//匹配到
                    flag=false;
                }
            }
            list.add(b);
            i+=count;
        }
        byte[] b=new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i]=list.get(i);
        }
        return b;
    }
    /**
     * @param flag 标志是否需要补高位
     * @param b 将一个byte转换成一个二进制的字符串
     * @return b对应的二进制字符串 按补码返回
     */
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        //如果是正数 还需要补高位
        int temp=b;
        if (flag) temp|=256;
        String str = Integer.toBinaryString(temp);
        if (flag) return str.substring(str.length()-8);//取后面8位
        else return str;


    }

    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建赫夫曼数
        Node root= createHuffmanTree(nodes);
        //根据赫夫曼树生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        //根据生成的赫夫曼编码压缩得到压缩后的赫夫曼编码的字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }
    //编写一个方法将字符串对应的byte[]数组 通过生成的哈夫曼编码表 返回一个哈夫曼编码处理后的一个byte[]数组
    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @param huffmanCodes 生成的哈夫曼编码
     * @return 返回哈夫曼编码处理后的一个byte[]数组
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用哈夫曼编码表 将传进来的byte[] 转成哈夫曼编码的对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder.toString());//10101000101111111100100010111
        //将 stringBuilder转换为byte[]数组
        //统计返回 byte[] 的长度
        int len;
        if (stringBuilder.length()%8==0){
            len=stringBuilder.length()/8;
        }else {
            len=stringBuilder.length()/8+1;
        }
        //存储压缩后的byte数组
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String strByte;
            if (i+8>stringBuilder.length()){//不够八位
                strByte=stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strBytes-->byte
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }
    /**
     *
     * @param bytes 接收字节数组
     * @return 返回List=> nodes
     */
    public static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历字节数组 统计每一个byte出现的个数
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte b:bytes) {
            counts.put(b,counts.getOrDefault(b,0)+1);
        }
        //把每一个key-value转换成一个Node对象 并加入到nodes集合
        for(Map.Entry<Byte, Integer> entry :counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
    //根据list创建对应的哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            //排序 从小到大
            Collections.sort(nodes);
            //取出第一颗最小二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data只有权值
            Node parent=new Node(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            //将处理过的两颗二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新二叉树加入到nodes
            nodes.add(parent);
        }
        //nodes最后的节点就是哈夫曼树的根节点
        return nodes.get(0);
    }
    //前序遍历
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("NUll");
        }
    }
    //生成哈夫曼树对应的哈夫曼编码
    //1 将哈夫曼编码表存放在Map<Byte,String>
    //  32=1 97=100;100=1100......
    static Map<Byte,String> huffmanCodes=new HashMap<Byte,String>();
    //2 在生成哈夫曼编码表时 需要拼接路径，定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder=new StringBuilder();
    //为了调用方便 我们重载getCodes
    private static Map<Byte,String> getCodes(Node root){
        if (root==null) return null;
        //处理root左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码，存放到huffmanCodes
     * @param node 传入的节点
     * @param code 路径：左子节点为0 右子节点为1
     * @param stringBuilder  用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder  stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将传入的code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node!=null){
            //判断当前node是叶子节点还是非叶子结点
            if (node.data==null){//非叶子结点
                //递归
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else {//叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }
}
//创建Node
class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null) this.left.preOrder();
        if (this.right!=null) this.right.preOrder();
    }
}
