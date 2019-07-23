package com.ecpss.dao;

import com.ecpss.spring.domain.User;

/**
 * Created by xc on 2019/6/20.
 */
public interface UserDao {
    
    User findById(Integer id);
    User findByOpen(String openId);
    
    void save(User user);
}
