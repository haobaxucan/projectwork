package com.ecpss.dm5;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by xc on 2019/8/4.
 */
public class Chaoshen {
    
    public static void main(String[] args) {
        Md5Hash md5 = new Md5Hash("123456");
        System.out.println(md5.toString());
        // 加密添加盐值 增大解密难度
        md5 = new Md5Hash("123","xc");
        System.out.println(md5.toString());
       
        Sha1Hash sha1= new Sha1Hash("123456", "aaa", 2);
        System.out.println(sha1);
    
        // 加密添加盐值 增大解密难度 2迭代两次
     
        md5 = new Md5Hash("123456","aaa",2);
   
        System.out.println(md5);
        SimpleHash result = new SimpleHash("MD5", "123", "xc", 1024);
        System.out.println(result.toString());
    }
}
