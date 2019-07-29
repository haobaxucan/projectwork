package com.ecpss.collection;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by xc on 2019/7/25.
 */
public class MapHash {
    @Test
    public void  t() {
        HashMap map = new HashMap(0);
        map.put("xc", "11");
        map.put("ca", "22");
        map.put("de", "33");
    
        map.forEach((x, v) -> System.out.println("x" + x + "v" + v));



//        int 类型四个字节，32位 ，向有移动16位
//        System.out.println(15 & 11111000);
//        map.put("aa2","de11");
//        map.put("aa4","de11");
//        map.put("aa5","de11");
//        map.put("aa333","de11");
//        map.put("a22","de11");
//        map.put("aa","de11");
//        map.put("aera","de11");
//        map.put("afsdcxa","de11");
//        map.put("a67a","de11");
//        map.put("a57a","de11");
//        map.put("a354a","de11");
//        map.put("a76a","de11");
//        map.put("aa111","de11");
//        map.put("ayrea","de11");
//        map.put("ayra","de11");
//        map.put("a2222a","de11");
//    }
    }
}
