package com.ecpss.service.impl;

import com.ecpss.User.Role;
import com.ecpss.User.User;
import com.ecpss.mapper.UserMapper;
import com.ecpss.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Slf4j
@Service
public class UserserviceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User query(User user) {
        log.info("user={}", user.getPassword()+","+user.getAccount());
        return userMapper.query(user);
    }
    
    @Override
    public List<User> pageQuery(Map map) {
        return userMapper.pageQuery(map);
    }
    
    @Override
    public int queryAllCount() {
        return userMapper.queryCount();
    }
    
    @Override
    public void insert(User user) {
        user.setPassword("123456");
        user.setCreatetime(new Date());
        userMapper.addUser(user);
    }
    
    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }
    
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
    
    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
    
    @Override
    public void delUsers(Map<String, Object> map) {
        userMapper.delUsers(map);
    }
    
    @Override
    public void insertUserRoles(Map<String, Object> map) {
        userMapper.addUserRoles(map);
    }
    
    @Override
    public List<Integer> queryRoleById(Integer id) {
        return userMapper.queryRoleById(id);
    }
}
