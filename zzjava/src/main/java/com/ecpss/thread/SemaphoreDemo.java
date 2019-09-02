package com.ecpss.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by xc on 2019/8/6.
 */
public class SemaphoreDemo {
    static  Semaphore semaphore = null;
    
   static class  Parking{
       public Parking() {
           semaphore= new Semaphore(5);
       }
       
       public void park(){
           try {
               semaphore.acquireUninterruptibly();//获取信号量
               long time = (long) (Math.random() * 10);
               System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
               Thread.sleep(time);
               System.out.println(Thread.currentThread().getName() + "开出停车场...");
               
               
           } catch (InterruptedException e) {
               e.printStackTrace();
           }finally {
               semaphore.release();
           }
       }
   }
    
   static class Car extends Thread{
       Parking parking=null;
       public Car(Parking parking) {
        this.parking=parking;
       }
    
       @Override
       public void run() {
        parking.park();
       }
   }
    
    public static void main(String[] args) throws Exception{
    
       Parking parking=new Parking();
       for(int i=0;i<15;i++){
           new Car(parking).start();//15个出租车停站 5个车位
       }
    
    }
}
