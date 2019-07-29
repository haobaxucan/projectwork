package com.ecpss.collection;

/**
 * Created by xc on 2019/7/26.
 */
public class Node {
    private int data;
    private Node next;
    
    public Node(int data) {
        this.data = data;
    }
    public void append(Node node){
        this.next=node;
    }
    
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        node.append(node2);
        System.out.println(node.next.data);
    }
}
