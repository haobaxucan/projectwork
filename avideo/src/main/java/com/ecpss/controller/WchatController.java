package com.ecpss.controller;

import com.ecpss.spring.config.WechatConfig;
import com.ecpss.spring.domain.User;
import com.ecpss.service.UserService;
import com.ecpss.utils.JsonData;
import com.ecpss.utils.JwtUtils;
import com.ecpss.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by xc on 2019/6/19.
 */
@Controller
public class WchatController {
    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private UserService userService;
    
    /**
     * 微信扫码登录主要就是凭借一段url
     * 拼接url
     *
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {
        
        String redirectUrl = wechatConfig.getOpenDirectUrl(); //获取开放平台重定向地址
        
        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK"); //进行编码
        
        String qrcodeUrl = String.format(wechatConfig.getOpenQrcodeUrl(), wechatConfig.getOpenAppId(), callbackUrl, accessPage);
        System.out.println(qrcodeUrl);
        return JsonData.buildSuccess(qrcodeUrl);
    }
    
    /**
     * 微信回调我们自己的接口
     * 微信扫码登录，回调地址
     */
    @RequestMapping("/callback")
    public void callBack(@RequestParam(value = "code", required = true) String code,
                         String state, HttpServletResponse response) {
        User user = userService.SaveWeChatUser(code);//我们可以进行保存用户信息
        System.out.println(code);
//使用JWT生成用户Token回写客户端
    
        //生成jwt
        if (user!=null) {
            String token = JwtUtils.geneJsonWebToken(user);
            try {
                /**
                 * state : http://www.xdclass.net
                 * 用于保持请求和回调的状态，授权请求后原样带回给第三方。
                 * 该参数可用于防止csrf攻击（跨站请求伪造攻击），
                 * 建议第三方带上该参数，可设置为简单的随机数加session进行校验
                 */
                /**
                 * 意思是跳转后携带了token过去
                 * state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转
                 */
                response.sendRedirect(state+"?token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(),"UTF-8"));
                
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
    }
    
    /***
     * 微信扫码支付回调
     */
    @RequestMapping("/order/callback")
    public void orderCallBack(HttpServletResponse response, HttpServletRequest request)throws Exception{
        InputStream inputStream=null;
        try {
             inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb=new StringBuffer();
        String line=null;
        try {
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
        try {
            inputStream.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    //商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致，
        // 防止数据泄漏导致出现“假通知”，造成资金损失。
            Map<String, String> map = WXPayUtil.xmlToMap(sb.toString());
        SortedMap sortedMap=WXPayUtil.getSortedMap(map);
        /**
         * 签名验证
         */

        if (WXPayUtil.isCorrectSign(sortedMap,wechatConfig.getKey())){
            //更新订单状态
//            从数据库中拿到未支付订单 videoOrder
//            判断不为空和订单状态为未支付
//            更新订单状态 videoOrder.setState(1) 添加到数据库saveOrUpdate(videoOrder)
        
        }
    
    
    }
    
}
