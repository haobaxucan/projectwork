package com.ecpss.tkmybatismapper.service;

import com.ecpss.tkmybatismapper.bean.User;
import com.ecpss.tkmybatismapper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @version 1.00
 * @date 2019/10/12
 */
@Service
public class CacheService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "defaultCache")
    public User selectOne(User user) {
        return userMapper.selectOne(user);
    }



}
