package com.ecpss.config;

import com.ecpss.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/8/26.
 */
@Configuration
public class CustomConfig {

    //    设置拦截器链
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("dsm") DefaultWebSecurityManager df){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(df);
        Map<String, String> fMap = new HashMap<String, String>();
        //拦截页面
        fMap.put("/admin/*","authc");
        fMap.put("/admin/","anon");
        fMap.put("/admin/login","anon");
        

        //被拦截返回登录页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fMap);
        return shiroFilterFactoryBean;
    }
    
    
    @Bean(name = "cust")
    public CustomRealm customRealm() {
    
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean(name = "dsm")
    public DefaultWebSecurityManager webSecurityManager(@Qualifier("cust")CustomRealm customRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        return defaultWebSecurityManager;
    }
    /**
     * 配置密码比较器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数： 意为加密几次
        hashedCredentialsMatcher.setHashIterations(1024);
        
        return hashedCredentialsMatcher;
    }
    
}
