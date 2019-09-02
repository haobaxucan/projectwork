package com.java_jiji.jvm;

import java.io.*;

/**
 * Created by xc on 2019/8/12.
 */
public class MyClassLoader extends ClassLoader {
    private String path;
    private String classLoaderName;
    
    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }
    /**
     * 关流先关闭
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name, b, 0, b.length);
    }
    
    private byte[] loadClassData(String name) throws Exception{
        name = path + name + ".class";
        InputStream is = null;
        ByteArrayOutputStream outputStream = null;
        try {
            is = new FileInputStream(new File(name));
            outputStream = new ByteArrayOutputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                outputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (is != null) {
                is.close();
            }
        }
    
        return outputStream.toByteArray();
    
    }
    
    public static void main(String[] args) throws Exception {
        String path="com\\java_jiji\\jvm\\";
        MyClassLoader myClassLoader = new MyClassLoader(path,"aa");
        Class c = myClassLoader.loadClass("book");
        System.out.println("ClassLoader:" + c.getClassLoader());
        Object instance = c.newInstance();
        
    }
}
