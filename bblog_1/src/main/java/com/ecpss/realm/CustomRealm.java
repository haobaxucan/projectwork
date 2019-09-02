package com.ecpss.realm;

import com.ecpss.domain.User;
import com.ecpss.service.Userservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomRealm extends AuthorizingRealm{
    @Autowired
    private Userservice userservice;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userservice.getUser(userName);
    
        if (user==null){
            return null;
        }
        //使用自己用户名作为颜值
        SimpleAuthenticationInfo   info=new SimpleAuthenticationInfo(userName,user.getPassword(),
                new SimpleByteSource(userName),"myReam");
       
        return info;
    }
}