package com.ecpss.thread;

/**
 * Created by xc on 2019/8/6.
 */
public class LoopNode{
    int data;
    LoopNode next=this;
    
    public void after(LoopNode loopNode){
       this.next=loopNode;
       loopNode.next=this;
    }
    public static void main(String[] args) {
        Node node=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        
        node.after(node2);
        node2.after(node3);
        System.out.println(node.next.data);//2
        System.out.println(node2.next.data);//1
    }
}
