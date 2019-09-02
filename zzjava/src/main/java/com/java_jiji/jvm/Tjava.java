package com.java_jiji.jvm;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.InputStream;

/**
 * Created by xc on 2019/8/9.
 */
public class Tjava /*extends TestGc*/{
   
    
    public static void main(String[] args) throws Exception{
    
        String a="/TestGc.java";
        InputStream inputStream=Tjava.class.getClassLoader().getResourceAsStream(a);
//        InputStream inputStream = Tjava.class.getResourceAsStream(a);
        ByteOutputStream byteOutputStream= new ByteOutputStream();
        int len = 0;
        byte b[] = new byte[1024];
      
            while ((len = inputStream.read(b)) != -1) {
                byteOutputStream.write(b, 0, len);
                System.out.println(new String(b));
            
       
    }
    }
}
