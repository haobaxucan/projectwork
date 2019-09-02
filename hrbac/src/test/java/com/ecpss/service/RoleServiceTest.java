package com.ecpss.service;

import com.ecpss.User.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xc on 2019/8/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void pageQuery() throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("start", 1);
        map.put("size", 2);
        List<Role> roles = roleService.pageQuery(map);
        System.out.println(roles.size());
    }
    
    @Test
    public void queryAllCount() throws Exception {
        int i = roleService.queryAllCount();
        System.out.println(i);
    }
    @Test
    public void queryAll() throws Exception {
        List<Role> roles = roleService.queryAll();
        System.out.println(roles.size());
    }
     @Test
    public void queryById() throws Exception {
        Role roles = roleService.queryById(3);
        System.out.println(roles.getName());
    }
    
}