package com.ecpss.service;

import com.ecpss.User.Role;
import com.ecpss.User.User;
import com.ecpss.mapper.UserMapper;
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
 * Created by xc on 2019/8/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    UserMapper userMapper;
    @Test
    public void query() throws Exception {
        User user=new User();
        user.setId(2);
        user.setUsername("aa");
        user.setAccount("de");
        user.setEmail("eee");
        int query = userService.updateUser(user);
        System.out.println(user.getAccount());
    }
    
    @Test
    public void del() throws Exception {
        userMapper.delete(31);
    }
    
    @Test
    public void assign() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userid",1);
        int unRoleIds[]={1,2,4};
        map.put("unRoleIds",unRoleIds);
        userService.insertUserRoles(map);
    }
    @Test
    public void test1() throws Exception {
        List<Integer> roles = userService.queryRoleById(29);
        System.out.println(roles);
    
    }
    
}