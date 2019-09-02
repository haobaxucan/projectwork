package com.ecpss.service;

import com.ecpss.User.Role;
import com.ecpss.User.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/28.
 */
public interface UserService {
    public User query(User user);
    
    List<User> pageQuery(Map map);
    
    int queryAllCount();
    
    
    void insert(User user);
    
    User queryById(Integer id);
    
    int updateUser(User user);
    void delete(Integer id);
    
    void delUsers(Map<String, Object> map);
    
    void insertUserRoles(Map<String, Object> map);
    
    List<Integer> queryRoleById(Integer id);
}
