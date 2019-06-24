package com.ecpss;

import com.ecpss.domain.User;
import com.ecpss.utils.JwtUtils;
import com.ecpss.utils.WXPayUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by xc on 2019/6/19.
 */
public class BaseTest {
 
    @Test
    public void t1(){//token
        User user=new User();
        user.setId(12);
        user.setName("xc");
        user.setHeadImg("www.html.com");
        user.setSex(1);
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }
    
    @Test
    public void parse(){
        Claims claims = JwtUtils.checkJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjEyLCJuYW1lIjoieGMiLCJpbWciOiJ3d3cuaHRtbC5jb20iLCJpYXQiOjE1NjEwMzI4MTgsImV4cCI6MTU2MTYzNzYxOH0.MQpitHs14zjcQd6aiqx96sDn8rohsuRq_n-pEPlmVKU");
        String name = (String) claims.get("name");
        
        System.out.println(name);
    
    }
    @Test
    public void test2(){
    
        String str="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&scope=snsapi_userinfo&response_type=code&state=STATE#wechat_redirect";
        String s=  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        String s1="https://open.weixin.qq.com/connect/qrconnect?appid=wxbdc5610cc59c1631& redirect_uri=https%3A%2F%2Fpassport.yhd.com%2Fwechat%2Fcallback.do&       response_type=code&scope=snsapi_login& state=3d6be0a4035d839573b04816624a415e#wechat_redirect";
        String s2="https://open.weixin.qq.com/connect/qrconnect?appid=wx30de7e8ac3923ce2& redirect_uri=http%3A%2F%2Fzyfq5d.natappfree.cc%2Fpub%2Flogin%2Fcallback1& response_type=code&scope=snsapi_login& state=1#wechat_redirect";
        
        System.out.println(str.equals(s));
        
    }
    @Test
    public void test3()throws Exception{
        Map map=new HashMap();
        map.put("aa","aa");
        map.put("bb","bb");
        map.put("cc","aa");
        String toXml = WXPayUtil.mapToXml(map);
        System.out.println(toXml);
    
        Map<String, String> toMap = WXPayUtil.xmlToMap(toXml);
        System.out.println(toMap);
        System.out.println(map);
    }
    
}
