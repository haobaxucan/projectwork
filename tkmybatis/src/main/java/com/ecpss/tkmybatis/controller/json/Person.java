package com.ecpss.tkmybatis.controller.json;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.tkmybatis.utils.FastJsonUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.thymeleaf.expression.Lists;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.00
 * @date 2019/10/22
 */
@Data
@Accessors(chain = true)
public class Person {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        List<Person> list=new ArrayList<>();
        Person person=new Person();
        person.setName("xx").setAge(12);
        Person person1=new Person();
        person1.setName("jie").setAge(123);

        list.add(person);
        list.add(person1);
        System.out.println(list);
        System.out.println(JSONObject.toJSONString(list));


    }
}
