package com.java_jiji.jvm;

/**
 * Created by xc on 2019/8/12.
 */
public class ClassLoaderDemo {
    
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader);//Launcher$AppClassLoader
        System.out.println(classLoader.getParent());//Launcher$AppClassLoader
        System.out.println(classLoader.getParent().getParent());//null
    }
}
