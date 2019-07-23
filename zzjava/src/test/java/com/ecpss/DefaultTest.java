package com.ecpss;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by xc on 2019/7/22.
 */

public class DefaultTest {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2,0.75f);
    @Test
    public void r()throws Exception{
           
                map.put(5, 55);
            
                new Thread("Thread1") {
                    public void run() {
                        map.put(7, 77);
                        System.out.println(map);
                    };
                }.start();
                new Thread("Thread2") {
                    public void run() {
                        map.put(3, 33);
                        System.out.println(map);
                    };
                }.start();
    
        new Thread("Thread3") {
            public void run() {
                map.put(4, 444);
                System.out.println(map);
            };
        }.start();
                
                
                Thread.sleep(1000);
                System.out.println(map);
            
            }
        
        
    @Test
    public void tg(){
    
   
    
       
    }
  

}
