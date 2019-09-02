package com.java_jiji.moshi.sington;

/**
 * Created by xc on 2019/8/21.
 */
public class SingletonDemo1 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }


}
class  Singleton{
    private static volatile Singleton instance;
    
    private Singleton() {}
    
   
    public static  Singleton getInstance() {
        if(instance == null) {
            synchronized (SingletonDemo1.class) {
                //每次线程都在这里等待，第一个线程创建的时候 volatile 更新到主存
                //第二个线程来的时候 对象已经创建了，不用继续创建了---解决了多线程的问题
                if (instance==null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}