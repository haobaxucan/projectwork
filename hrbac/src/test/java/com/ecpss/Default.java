package com.ecpss;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xc on 2019/8/29.
 */
public class Default {
    @Test
    public void relist(){

        List<String> list=new ArrayList();
        list.add("aa");
        list.add("bb");
        if(list.contains("aa")){
            System.out.println("td");
        }else {
            System.out.println("dafalse");
        }
    }
    
    @Test
    public void re(){
        User<String> user=new User<>();
        List<String> list=new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("bb");
        user.setUsers(list);
        System.out.println(user.getUsers().size());
    
    }

}
@Data
class User<T>{
    private List<T> users;
    private int age;
}