package com.ecpss.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xc on 2019/8/7.
 */
public class CountDownLatchDemo {
   static CountDownLatch countDownLatch=new CountDownLatch(5);
   static  int count=0;
    
    static  class Boss extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("老板来了等待");
                countDownLatch.await();
                System.out.println("好了开始开会，老板做事");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Worker extends Thread{
        
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"员工"+ count++ +"来了,开始做事");
            } finally {
                countDownLatch.countDown();
            }
        }
    
    }
    
    public static void main(String[] args) {
        new Boss().start();   new Boss().start();
        for (int i=0; i<5;i++){
            new Worker().start();
        }
        
    }
}
