package com.java_jiji.moshi.proptype;

/**
 * Created by xc on 2019/8/21.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep sheep2=sheep;
        System.out.println(sheep2==sheep);//true  ---浅拷贝
        Sheep sheep1 = (Sheep) sheep.clone();
        System.out.println(sheep==sheep1);//false---深拷贝
//        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep5 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        //....
//
//        System.out.println(sheep);
//        System.out.println(sheep2);
//        System.out.println(sheep3);
//        System.out.println(sheep4);
//        System.out.println(sheep5);
    }
    
}
