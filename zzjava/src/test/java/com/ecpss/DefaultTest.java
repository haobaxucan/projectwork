package com.ecpss;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        Map<String,String> map=new HashMap();
        map.put("aa","bb");
        Set<Map.Entry<String, String>> entries = map.entrySet();
    
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(key+""+value);
    
    
        }
        
    
    
    }
  

}
