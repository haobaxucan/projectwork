package com.ecpss.demo;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Created by xc on 2019/8/4.
 */
public class Myrealm extends AuthorizingRealm {
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName =(String) principals.getPrimaryPrincipal();
//        更具账号去数据库中查询出所有的角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 添加角色
        info.addRole("xc");
    
        // 添加权限
        info.addStringPermission("xc:update");
      
        return info;
      
       
    }
    
    //只认证账号 密码自动认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取账号信息
        String principal = (String) token.getPrincipal();
        // 正常逻辑此处应该根据账号去数据库中查询，此处我们默认账号为 root 密码123456
        // 验证账号
        if(!"root".equals(principal)){
            // 账号错误
            return null;
        }
        //String pwd = "123456";
        // 12345 根据 盐值 aaa 加密获取的密文
        //88316675d7882e3fdbe066000273842c  1次迭代的密文
        String pwd = "88316675d7882e3fdbe066000273842c";
        // 验证密码
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                principal, pwd,new SimpleByteSource("aaa"),"myrealm");
        return info;
    }

}
