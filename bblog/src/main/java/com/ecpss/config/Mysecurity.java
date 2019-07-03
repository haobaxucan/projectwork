package com.ecpss.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by xc on 2019/6/25.
 */
@EnableWebSecurity
public class Mysecurity extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则                      ---静态资源允许速所有人访问
        http.authorizeRequests().antMatchers("/css/**", "js/**","/","/login","/index").permitAll().
                antMatchers("/level1/**").hasRole("USER").
                antMatchers("/level2/**").hasRole("vip2").
                antMatchers("/level3/**").hasRole("vip3");
        //开启登陆功能
        //1.发送登陆/login请求，如果没有登陆页面，就来到登陆页面
        //2.重定向到/login？error表示认证失败
        http.formLogin();
        //开启注销功能，访问/login注销，清空session
        
        http.logout().logoutSuccessUrl("/");//注销成功后来到首页
        //开启记住我功能
        http.rememberMe();
    }
}