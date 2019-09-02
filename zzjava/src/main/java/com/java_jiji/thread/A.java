package com.java_jiji.thread;

/**
 * Created by xc on 2019/8/9.
 */
public class A {
    
    public int var;
    
    private static A a = new A();
    
    private A(){}
    
    public static A getInstance(){
        return a;
    }
    
    public synchronized void method1(){
        var = 3;
    }
    
    public synchronized void method2(){
        int b = var;
    }
    
    public void method3(){//A@466
        synchronized(new A()){ //注意这里和method1 method2 用的可不是同一个锁哦
            var = 4;
        }
    }
    
    public static void main(String[] args) {
        A a=new A();
        a.method1();
        a.method2();
    }

    

}
