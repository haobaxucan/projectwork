package com.java_jiji.moshi.proptype;

/**
 * Created by xc on 2019/8/21.
 */
public class Sheep {
    
    private String name;
    private int age;
    private String color;
    public Sheep(String name, int age, String color) {
        super();
        this.name = name;
        this.age = age;
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Sheep [name=" + name + ", age=" + age + ", color=" + color + "]";
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
    
        Sheep sheep=null;
        try {
        
        }catch (Exception e){
             sheep=(Sheep) super.clone();
        }
        return sheep;
    }
}
