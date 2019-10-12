package com.ecpss.wx_public_number.controller;

import com.ecpss.wx_public_number.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @version 1.00
 * @date 2019/10/10
 */
@Controller
public class TokenController {
    private static final  String url="https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=%s&secret=%s";
    private static final String APPID="wx30de7e8ac3923ce2";
    private static final String APPSECTET="e98584dc55427d15b60c4937084e530d";
    @RequestMapping("/token")
    @ResponseBody
    public String index(){
        String qurl=String.format(url,APPID,APPSECTET);
        System.out.println(qurl);
        Map<String, Object> map = HttpUtils.doGet(qurl);
        System.out.println(map.get("access_token"));
        return "token";
    }
}
