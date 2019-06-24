package com.ecpss.interceptor;

import com.ecpss.utils.JsonData;
import com.ecpss.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xc on 2019/6/20.
 */


public class LoginInterceptor implements HandlerInterceptor {
    private static final Gson gson = new Gson();
    
    /**
     * 进入controller之前拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("头域里面token"+token);
        if (token == null) {//请求头为空的时候
            token = request.getParameter("token");
            System.out.println("参数里面token"+token);
        }
        
        if (token != null) {
            Claims claims = JwtUtils.checkJWT(token);
            Integer id = (Integer) claims.get("id");
            
            request.setAttribute("userid", id);
            return true;
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));
        return false;
    }
    
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws IOException {
        
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(obj));
        writer.close();
        
        response.flushBuffer();
        
    }
}
