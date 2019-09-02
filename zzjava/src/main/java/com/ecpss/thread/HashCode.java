package com.ecpss.thread;

/**
 * Created by xc on 2019/8/6.
 */
public class HashCode {
    public static void main(String[] args) {
        CasDemo casDem=new CasDemo();
        casDem.hashCode();
        int i = "1".hashCode();
        int a = "1".hashCode();
        System.out.println(i+" --"+a);
        
    }
}
