package com.ecpss.controller;

import com.ecpss.User.Permission;
import com.ecpss.User.User;
import com.ecpss.service.PermissionService;
import com.ecpss.service.UserService;
import com.ecpss.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xc on 2019/8/28.
 */
@Slf4j
@Controller
public class DefaultController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model) {
        log.info("user={}", user.getPassword() + ",--" + user.getAccount());
        User query = userService.query(user);
        log.info("queryUser={}", user.getPassword() + ",--" + user.getAccount());
        if (query == null) {
            model.addAttribute("info", "账号或者密码错误");
            return "login";
        } else {
            return "main";
        }
    }
    
    //ajax 提交不返回页面
    @RequestMapping("/doAjaxLogin")
    @ResponseBody
    public Object doAjaxLogin(User user, Model model, HttpServletRequest request) {
        ResultVo vo = new ResultVo();
        User query = userService.query(user);
        if (query == null) {
            vo.setSucc(false);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc",query.getAccount());
            log.info("acc={}",query.getAccount());
            List<Permission> permissions = permissionService.queryPermissionByUser(user);
    
            vo.setSucc(true);
        }
        return vo;
    }
    
    @RequestMapping("/main")
    public String main() {
        
        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
            session.removeAttribute("acc");
//            session.invalidate();
            return "redirect:/login";
    }
    
}
