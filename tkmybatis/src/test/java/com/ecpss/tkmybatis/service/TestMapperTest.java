package com.ecpss.tkmybatis.service;

import com.ecpss.tkmybatis.com.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapperTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test1(){
        TestUser testUser=new TestUser(null,"dec","re");
        TestUser user = userService.getOne(testUser);
        System.out.println(testUser.getName());

    }

}