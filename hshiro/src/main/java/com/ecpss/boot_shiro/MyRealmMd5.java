package com.ecpss.boot_shiro;

import com.ecpss.boot_shiro.bean.User;
import com.ecpss.boot_shiro.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/8/4.
 */
@Component
public class MyRealmMd5 extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");
        String userName = (String) principals.getPrimaryPrincipal();
        User user = userMapper.getUserByName(userName);
        String pers = user.getPers();
        
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermission(pers);
        
        return info;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证---");
        String userName = (String) token.getPrincipal();
        User user = userMapper.getUserByName(userName);
        if (user==null){
            return null;
        }
        
        String password=user.getPassword();
        
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userName,password,
                ByteSource.Util.bytes(user.getUsername()),"upass");
        
        return info;
    }
}
