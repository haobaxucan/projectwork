package com.ecpss.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xc on 2019/8/5.
 */
public class CasDemo {
    AtomicInteger integer=new AtomicInteger(0);
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    
    
    public void incr(){
        int andIncrement = integer.getAndIncrement();
        System.out.println("当前值"+integer.get());
    }
    public void task(){
        for(int i=0;i<100;i++){
            executorService.execute(this::incr);
        }
    }
    
    public static void main(String[] args) {
        CasDemo casDemo=new CasDemo();
        casDemo.task();
        Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
            
            }
        });
        
    }
}
