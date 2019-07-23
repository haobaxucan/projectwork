package com.ecpss.test.coll;

import java.util.HashMap;

/**
 * Created by xc on 2019/7/22.
 */
public class HashMapdemo {
    public void aa(){
        HashMap<Object, Object> m = new HashMap<>();
        m.put("aa","aa");
    }
    public static void main(String[] args) {
    
        HashMap<Object, Object> m = new HashMap<>(8);
        m.put("a","dd");
        m.put("a1","dd");
        m.put("a2","dd");
        System.out.println(m);
        
        int n=30;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }
}
