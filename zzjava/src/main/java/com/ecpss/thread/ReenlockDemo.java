package com.ecpss.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xc on 2019/8/5.
 */
public class ReenlockDemo {
    public int count =0;
    public ReentrantLock lock=new ReentrantLock(true);
    public int incr() {
        try {
            lock.lock();
            lock.lock();
          return count++;
        
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
    
    public static void main(String[] args) throws Exception{
        Thread t1[]=new Thread[100];
        ReenlockDemo reenlockDemo=new ReenlockDemo();
        for(int i=0;i<1;i++){
            t1[i]=new Thread(new Runnable() {
                @Override
                public void run() {
    
                    for (int i=0;i<100;i++) {
                       
                        reenlockDemo.incr();
                    }
                }
            });
        }
        
        for(int i=0;i<2;i++){
            t1[i].start();
        }
        for(int i=0;i<2;i++){
            t1[i].join();
        }
        System.out.println(reenlockDemo.incr());
    }
    

}
