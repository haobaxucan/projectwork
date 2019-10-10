package com.ecpss.login.repeatform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.00
 * @date 2019/10/9
 */
@Controller
@Slf4j
public class NoRepeatFormController{

    @RequestMapping("/token")
    public String token(){
        return "token/token";
    }
    @RequestMapping("/login_token")
    public String login_token(String name, String password, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("aaa","123");
        map.forEach((x,y)->{
            if(x.equals("aaa")&&y.equals("123")){
                String token = TokenProcessor.getInstance().generateTokeCode();
                session.setAttribute("token",token);
                log.info("生成token={}",token);
            }
        });
        return "token/res";
    }

    @RequestMapping("/result")
    public String result(String token,String name,HttpSession session){
        log.info("token={}",token);

        String sessionToken = (String)session.getAttribute("token");

        if(sessionToken==null ||!sessionToken.equals(token)){
            System.out.println("表单重复提交");
            return "token/res";
        }else {
            session.removeAttribute("token");
            System.out.println("用户处理了"+name+"的请求");
        }
        return "token/res";
    }


}
