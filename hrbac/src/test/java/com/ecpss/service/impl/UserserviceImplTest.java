package com.ecpss.service.impl;

import com.ecpss.User.User;
import com.ecpss.service.UserService;
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
public class UserserviceImplTest {
    @Autowired
    UserService userService;
    @Test
    public void queryLike() throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("start", 1);
        map.put("size", 3);
        map.put("queryText","aa");
        
        List<User> users = userService.pageQuery(map);
        System.out.println(users.size());
    }
    @Test
    public void add() throws Exception {
        for (int i=0;i<6;i++) {
            User user=new User();
            user.setUsername("xucan"+i+1);
            user.setAccount("choahen"+i+1);
            userService.insert(user);
        }
    
    }
    
}