package com.ecpss.mapper;

import com.ecpss.User.Role;
import com.ecpss.User.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {
//    @Select("select * from user where account=#{account} and password=#{password}")
    User query(User user);
    User queryById(Integer id);
    
    List<User> pageQuery(Map map);
    
    int queryCount();
    
    List<User> queryLike(Map map,String username);
    
    void addUser(User user);
    
    int updateUser(User user);
    void delete(Integer id);
    
    
    void delUsers(Map<String, Object> map);
    
    void addUserRoles(Map<String, Object> map);
    
    List<Integer> queryRoleById(Integer id);
}
