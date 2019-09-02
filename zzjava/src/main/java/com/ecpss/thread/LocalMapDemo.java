package com.ecpss.thread;

/**
 * Created by xc on 2019/8/7.
 */
public class LocalMapDemo {
    
    
    public ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    
    public void get() {
        for (int i = 0; i < 3; i++) {
            
            threadLocal.set(threadLocal.get() + 1);
            System.out.println(Thread.currentThread().getName() + "--" + threadLocal.get());
        }
        
    }
    
    public static void main(String[] args) {
        
        LocalMapDemo localMapDemo = new LocalMapDemo();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    localMapDemo.get();
                }
            }).start();
        }
    }
    
}
