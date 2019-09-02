package com.java_jiji.jvm;

/**
 * Created by xc on 2019/8/7.
 */
public class TestGc {

    public volatile static int a=12;
    public  int aa=1111112;
    static {
        System.out.println("-------");
    }
    {
        System.out.println("1111");
    }

    public TestGc(int aa) {
        System.out.println("aaaaaaaaaa");
        this.aa = aa;
    }

    public TestGc() {

        System.out.println("dedee");
    }





}
