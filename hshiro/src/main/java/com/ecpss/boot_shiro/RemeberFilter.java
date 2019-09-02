package com.ecpss.boot_shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by xc on 2019/8/5.
 */
//public class RemeberFilter extends FormAuthenticationFilter {
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        Subject subject = getSubject(request, response);
//        Session session = subject.getSession();
//        // 记住密码，没有登录isAuthenticated()肯定为false
//        if(!subject.isAuthenticated()
//                &&subject.isRemembered()
//                &&session.getAttribute("msg")==null){
//            System.out.println("记住的用户是:"+subject.getPrincipal());
//            session.setAttribute("msg", "remember中保存的信息");
//        }
//        return subject.isAuthenticated()||subject.isRemembered();
//    }
//
//}
