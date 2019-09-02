package com.ecpss.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by xc on 2019/8/12.
 */
@WebServlet("/web")
public class Myservlet implements Servlet {
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init -----------");
    }
    
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    
    }
    
    @Override
    public String getServletInfo() {
        return null;
    }
    
    @Override
    public void destroy() {
        System.out.println("destory -----------");
    }
}
