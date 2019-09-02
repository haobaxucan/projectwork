package com.ecpss.service.impl;

import com.ecpss.dao.UserDao;
import com.ecpss.domain.User;
import com.ecpss.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements Userservice{
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser(String userName) {
        return userDao.getByName(userName);
    }
}
