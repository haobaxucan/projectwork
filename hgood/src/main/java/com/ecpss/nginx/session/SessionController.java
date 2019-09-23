package com.ecpss.nginx.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class SessionController {
    @Value("${server.port}")
    private Integer projectPort;// 项目端口
    @RequestMapping("/create")
    @ResponseBody
    public String createSession(HttpSession session, String name) {
        session.setAttribute("name", name);
        return "当前项目端口： "+projectPort+" 当前sessionId :" + session.getId() + "在Session中存入成功！";
    }
    @RequestMapping("/get")
    @ResponseBody
    public String getSession(HttpSession session) {
        return "当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() +
                "  获取的姓名:" + session.getAttribute("name");
    }
}
