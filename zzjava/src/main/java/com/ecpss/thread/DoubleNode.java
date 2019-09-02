package com.ecpss.thread;

/**
 * Created by xc on 2019/8/6.
 */
public class DoubleNode {
    DoubleNode pre=this;// 上一个节点  循环链表时：一个节点的时候都指向自身
    DoubleNode next=this;
    int data;
    public DoubleNode next(){
        return this.next;
    }
    public DoubleNode(int data) {
        this.data = data;
    }
    public void after(DoubleNode node){//赋值语句左边指向右边
//       this.next=node;
//       node.pre=this;
//
//       this.pre=node.next;
//
//       node.next=this.pre;
        //当前节点的下一个节点
        DoubleNode next = this.next;
        node.pre=this;//node.pre→this 意思是新节点的前一个节点指向当前节点
        this.next=node;
        node.next=next;
        next.pre=node;
    
    }
    
    public static void main(String[] args) {
        DoubleNode doubleNode=new DoubleNode(1);
        DoubleNode doubleNode2=new DoubleNode(2);
        DoubleNode doubleNode3=new DoubleNode(3);
        doubleNode.after(doubleNode2);
        doubleNode2.after(doubleNode3);
        System.out.println(doubleNode.next.data);//2
        System.out.println(doubleNode.pre.data);//3
        System.out.println(doubleNode3.pre.data);//2
        
//        this.next=node   node.next=this
    
    }
    
}

