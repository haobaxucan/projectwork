package com.java_jiji.moshi.proptype.sclone;

/**
 * Created by xc on 2019/8/21.
 */
public class Student implements Cloneable{
    
    private String name;
    private int age;
    private Professor professor;
    
    public Student(String name, int age, Professor professor){
        this.name = name;
        this.age = age;
        this.professor = professor;
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
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public Object clone(){
        Student o = null;
        try {
            o = (Student)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        o.professor = (Professor)professor.clone();
        return o;
    }
    
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", professor="
                + professor + "]";
    }
    
}

 class Professor implements Cloneable{
    
    private String name;
    private int age;
    
    public Professor(String name, int age){
        this.name = name;
        this.age = age;
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
    
    @Override
    public Object clone(){
        Object o = name;
        try {
            o= super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    
    @Override
    public String toString() {
        return "Professor [name=" + name + ", age=" + age + "]";
    }
    
}

 class DeepClone {
    public static void main(String[] args) {
        Professor p=new Professor("wangwu",50);
        Student s1=new Student("zhangsan",18, p);
        System.out.println(s1);
        
        Student s2=(Student)s1.clone();
        s2.getProfessor().setName("maer");
        s2.getProfessor().setAge(40);
        System.out.println("复制后的：s1 = " + s1);
        System.out.println("复制后的：s2 = " + s2);
        System.out.println(s1==s2);
    }
}
