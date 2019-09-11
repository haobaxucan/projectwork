package com.ecpss.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by xc on 2019/9/3.
 */
@Accessors(chain=true)
@Data
public class User {
 
    
    private long id;
   
    private String name;
 
    private String address;
   
    private Integer age;
    
    public static void main(String[] args) {
        User user = new User().setName("xc").setAge(12).setAddress("湖南");
    }
}
