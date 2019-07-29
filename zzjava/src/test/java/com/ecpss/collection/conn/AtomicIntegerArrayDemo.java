package com.ecpss.collection.conn;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerArrayDemo {
    static AtomicInteger arr = new AtomicInteger(10);
    
    public int de1(){
        return arr.getAndIncrement();
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArrayDemo a=new AtomicIntegerArrayDemo();
        Thread[] ts=new Thread[10];
        //创建10条线程
        for(int k=0;k<10;k++){
        ts[k]= new Thread(()->a.de1());
        }
        //启动10条线程
    
        for(int k=0;k<10;k++){ts[k].start();}
        for(int k=0;k<10;k++){ts[k].join();}
        //执行结果
        //[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
        System.out.println(arr);
    }
}
