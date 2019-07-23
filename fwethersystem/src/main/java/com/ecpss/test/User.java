package com.ecpss.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xc on 2019/7/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "u")
public class User extends Person implements Serializable{
    private String name;
    private Integer age;
    private String address;
    private String password;
    
    public User(String name, Integer age, String address, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.password = password;
    }
    
    List<String> sub=new ArrayList<>();
    
    public static void main(String[] args) {
//        User person = new Person(); //父类不可以当子类
        Person user = new User();
//        user.
    }
    
}
