package com.ecpss.tkmybatis.service;

import com.ecpss.tkmybatis.com.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xc
 * @version 1.00
 * @date 2019/9/25
 */
@Service
public class UserServiceImpl {
    @Autowired
    private TestMapper testMapper;
    public void  addUser(TestUser testUser){
        testMapper.insert(testUser);
    }

    public TestUser  getOne(TestUser testUser){
        TestUser user = testMapper.selectOne(testUser);
        return  user;
    }



}
