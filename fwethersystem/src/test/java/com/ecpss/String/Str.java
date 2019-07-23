package com.ecpss.String;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xc on 2019/7/14.
 */
@Slf4j
public class Str {
    @Test
    public void t11(){
        String string="abc def.txt";
        char c = string.charAt(1);//b
        log.info("数字={}",c);
    
    
        boolean de = string.contains("de");
        System.out.println(de);
        String c1 = String.format("name%s", "c");
        System.out.println(c1);//namec
    
        int i1 = string.indexOf(".");
        System.out.println(i1);
        int i2 = string.lastIndexOf(".");//7
        System.out.println(i2);//7
        String substring = string.substring(2);//c def.txt
        String substring1 = string.substring(1, 5);//个数end-start
        System.out.println(substring1);
//        string
        String substring2 = string.substring(0,string.lastIndexOf("."));
        System.out.println(substring2+"--");//abc def--
    
    
    }
    @Test
    public void  s1(){
        String s1="   aa  aa  ";
        String s="abc def.txt";
        int length = s1.trim().length();
        System.out.println(length);
        String substring = StringUtils.substring(s, 1);
        System.out.println(substring);
    
    }
    
    
    
    
    @Before
    public void t1(){
        System.out.println("开始测试");
    }
    @After
    public void t2(){
        System.out.println("测试结束");
    }
}
