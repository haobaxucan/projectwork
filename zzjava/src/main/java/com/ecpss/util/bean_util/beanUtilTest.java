package com.ecpss.util.bean_util;

import org.springframework.beans.BeanUtils;

/**
 * Created by xc on 2018/10/17.
 */
public class beanUtilTest {
    public static void main(String[] args) {
        User user = new User();
        user.setId(11);
        user.setName("cc");
        user.setAge(12);
        EsUser esUser = new EsUser();
        BeanUtils.copyProperties(user, esUser);
        System.out.println(esUser.getId()+"--"+esUser.getName());
        
    }
}
