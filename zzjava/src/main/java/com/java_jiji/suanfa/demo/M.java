package com.java_jiji.suanfa.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/8/21.
 */
public class M {
    public static void main(String[] args) {
        "sa".replaceAll("a","vv");
    
        Map<String, String> stringMap=new HashMap<>();
          stringMap.put("a","da");
          stringMap.put("aa","da");
            stringMap.forEach((x,y)->{
                System.out.println(x);
                System.out.println(y);
            });
            

    }
}
