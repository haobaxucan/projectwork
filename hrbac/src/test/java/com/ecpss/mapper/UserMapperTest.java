package com.ecpss.mapper;

import com.ecpss.User.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xc on 2019/8/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void r(){
        User user = userMapper.queryById(1);
        System.out.println(user.getAccount()+"--"+user.getPassword());
    }
    
}