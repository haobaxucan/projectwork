package com.ecpss.test.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xc on 2019/7/31.
 */
public class conhash {
    
    public static void main(String[] args) {
//        HashMap<Object, Object> a = new HashMap<>();
//        a.put("a","c");
    
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("","da");
        concurrentHashMap.put(null,"da");
    
    }
    
}



