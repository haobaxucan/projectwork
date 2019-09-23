package com.ecpss.login;

import com.ecpss.cart.CookieUtil;
import io.searchbox.strings.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   
//        判断拦截的方法 是否有注解，需要被拦截
        HandlerMethod method = null;
        LoginRequired loginRequired=null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
            loginRequired = method.getMethodAnnotation(LoginRequired.class);
        }
       
        if(loginRequired==null){
            return  true;
        }
        String token = "";
    
        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        if (StringUtils.isNotBlank(oldToken)) {
            token = oldToken;
        }
        
        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }
        String success = "success";
        StringBuffer url = request.getRequestURL();
        
        
        boolean loginSuccess = loginRequired.loginSuccess();
        if (loginSuccess){// 必须登录才能使用
            //重定向会passport登录
            if (!success.equals("success"))  {
                StringBuffer requestURL = request.getRequestURL();
                response.sendRedirect("http://localhost:8033/login?ReturnUrl="+requestURL);
                return false;
            }
    
            // 需要将token携带的用户信息写入
            request.setAttribute("memberId","userid");
            //验证通过，覆盖cookie中的token
            if(StringUtils.isNotBlank(token)){
                CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
            }
        }
        
    
   
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    
    }
}
