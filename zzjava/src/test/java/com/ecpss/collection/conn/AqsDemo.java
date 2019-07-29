package com.ecpss.collection.conn;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xc on 2019/7/26.
 */
public class AqsDemo  extends AbstractQueuedSynchronizer{
    
    
    ReentrantLock lock=new ReentrantLock();
    public void lock(){
        lock.lock();
    
        System.out.println("111111111-------");
        lock.lock();
        System.out.println("22222222------");
        lock.unlock();
        System.out.println("3333333333-------");
        lock.unlock();
    }
    
    public static void main(String[] args) {
        AqsDemo aqsDemo=new AqsDemo();//多线程调用同一个方法
        
        
        new Thread(()->{
            aqsDemo.lock();
            System.out.println(Thread.currentThread().getName());
        
        }).start();
    
        new Thread(()->{
            aqsDemo.lock();
            System.out.println(Thread.currentThread().getName());
        
        }).start();
    }
}
