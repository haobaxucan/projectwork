package com.ecpss.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xc on 2019/8/7.
 */
public class CyclicBarrierTest {
    static int a[] = {1, 2, 3};
    static int b[] = {1, 2, 3};
    static int c[] = {1, 2, 3};
   static int sum = 0;
   static CyclicBarrier cyclicBarrier;
   
   static class work extends Thread {
        int a[];
        
        public work(int a[]) {
            this.a = a;
            
        }
        @Override
        public void run() {
    
            System.out.println(Thread.currentThread().getName() + "來了");
            try {
                for (int i = 0; i < a.length; i++) {
                    sum += a[i];
                }
                System.out.println(sum);
                cyclicBarrier.await();
             
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
           
            
            
        }
    }
    
    
    public static void main(String[] args) {
        cyclicBarrier=new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("最后的sum="+sum);
            }
        });
        new work(a).start();
        new work(b).start();
        new work(c).start();

    }
}
