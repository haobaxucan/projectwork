package com.ecpss.dao;

import com.ecpss.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xc on 2019/8/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    
    @Test
    public void test1(){
        User xc = userDao.getByName("xc");
        System.out.println(xc.getPassword());
    }
    
}