package com.ecpss.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by zejian on 2017/7/2.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public class ABADemo {
    
    static AtomicInteger atIn = new AtomicInteger(100);
    
    //初始化时需要传入一个初始值和初始时间
    static AtomicStampedReference<Integer> atomicStampedR =
            new AtomicStampedReference<Integer>(200,0);
    
    
    static Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            //更新为200
            atIn.compareAndSet(100, 200);
            //更新为100
            atIn.compareAndSet(200, 100);
        }
    });
    
    
    static Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag=atIn.compareAndSet(100,500);//t1 t2 这里是aba 问题
            System.out.println("flag:"+flag+",newValue:"+atIn);
        }
    });
    
    
    static Thread t3 = new Thread(new Runnable() { //t3 //t4 解决
        @Override
        public void run() {
            int time=atomicStampedR.getStamp();
            //更新为200
            atomicStampedR.compareAndSet(100, 200,time,time+1);
            //更新为100
            int time2=atomicStampedR.getStamp();
            atomicStampedR.compareAndSet(200, 100,time2,time2+1);
        }
    });
    
    
    static Thread t4 = new Thread(new Runnable() {
        @Override
        public void run() {
            int time = atomicStampedR.getStamp();
            System.out.println("sleep 前 t4 time:"+time);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag=atomicStampedR.compareAndSet(100,500,time,time+1);
            System.out.println("flag:"+flag+",newValue:"+atomicStampedR.getReference());//所以这里还是500
        }
    });
    
    public static  void  main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        t3.start();
        t4.start();
        /**
         * 输出结果:
         flag:true,newValue:500
         sleep 前 t4 time:0
         flag:false,newValue:200
         */
    }
}