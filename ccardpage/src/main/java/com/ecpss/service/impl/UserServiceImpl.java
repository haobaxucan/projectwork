package com.ecpss.service.impl;

import com.ecpss.dao.UserDao;
import com.ecpss.domain.User;
import com.ecpss.exception.Response;
import com.ecpss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by xc on 2019/6/25.
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public Response createUser(User user) throws Exception {
        userDao.Save(user);
        return new Response(user);
    }
    
}
