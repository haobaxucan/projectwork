package com.ecpss.test;

import com.ecpss.util.utiltest.ZipUtil;

import java.io.ByteArrayInputStream;

/**
 * Created by xc on 2019/7/12.
 */
public class Money1 {
    
    public static void main(String[] args) throws Exception{
        String s = "C:\\Users\\Raytine\\Desktop\\poi.txt";
//        File f = ZipUtil.zip(s);
//        System.out.println(f);
//        File file=new File("a.txt");
//        file.createNewFile();
    
        ByteArrayInputStream inputStream=new ByteArrayInputStream("djeioh vhaodh de a".getBytes());
        ZipUtil.zipInstream(inputStream,"chao.doc","aa");
        
    }
}
