package com.ecpss.thread;

/**
 * Created by xc on 2019/8/6.
 */
public class Node {//一个节点
    int data;
    Node next;//下一个节点
    
    public Node(int data) {//创建节点的时候赋值
        this.data = data;
    }
//    为节点追加节点
    
    
    public void append(Node node) {
        Node currNode = this;
        while (true) {
            Node nextNode = currNode.next;
            
            if (nextNode == null) {
                break;
            }
            currNode = nextNode;
            
        }
        //跳出while循环当前节点最后没有节点
        currNode.next = node;
        
    }
    
    //    获取下一个节点
    public Node next() {
        return next;
    }
//删除下一个节点
    
    public void removeNext() {//取出下下个节点，设为当前节点
        Node nextNode = next().next;
        this.next = nextNode;
        
    }
    
    //追加某个节点到当前节点的下一个位置
    public void after(Node node) {
        Node next = this.next;//取出当前节点的下一个节点
        this.next = node;//当前节点的下一个节点指向新的节点
        node.next = next;//新的节点下一个节点指向第一步取出的节点
    }
    
    
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node.append(node2);
        node.append(node3);
        System.out.println(node.next().next().data);//3
        node.removeNext();
        System.out.println(node.next().data);//3
        
    }
}