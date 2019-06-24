package com.ecpss.controller;

import com.ecpss.config.WechatConfig;
import com.ecpss.domain.User;
import com.ecpss.utils.IpUtils;
import com.ecpss.utils.JwtUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/6/18.
 */
@Controller
public class TestController {
    
    
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }
    
    @Autowired
    WechatConfig wechatConfig;
    
    @ResponseBody
    @RequestMapping("/we")
    public String weChat() {
        return wechatConfig.getAppId();
    }
    
    /**
     * 微信使用我们提供的token，通过算法生成签名，
     * 然后将这几个参数（signature ，timestamp ，nonceechostr ）传给我们，
     * 我们服务端通过我们提供的token按照微信的算法得出一个值，如果是同一个值说明token有效
     *
     * @param request
     * @param echostr
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/yan", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String getWxUserInfo(HttpServletRequest request,
                                @RequestParam(required = false) String echostr,
                                @RequestParam(required = false) String signature,
                                @RequestParam(required = false) String timestamp,
                                @RequestParam(required = false) String nonce) {
        try {
            //只需要把微信请求的 echostr, 返回给微信就可以了
            System.out.println("测试来过===================" + echostr);
            System.out.println("测试来过===================" + signature);
            System.out.println("测试来过===================" + timestamp);
            System.out.println("测试来过===================" + nonce);
            return echostr;
        } catch (Exception e) {
            System.out.println("测试微信公众号的接口配置信息发生异常：");
            return "错误！！！";
        }
        
    }
    /**
     * 测试token
     */
    
    @RequestMapping("/index")
    public void index(HttpServletResponse response) throws Exception{
        User user=new User();
        user.setId(12);
        user.setName("xc");
        user.setHeadImg("www.html.com");
        String token = JwtUtils.geneJsonWebToken(user);
        response.sendRedirect("https://www.baidu.com?token="+token);
    }
    
    @RequestMapping("/red")
    public String redirect(HttpServletResponse response) throws Exception{
        User user=new User();
        user.setId(12);
        user.setName("xc");
        user.setHeadImg("www.html.com");
        String token = JwtUtils.geneJsonWebToken(user);
        return "redirect:http://localhost:8081/?token="+token;//本地token
    }
    
    @RequestMapping("/")
    public String shouYe(){
        return "index";
    }
    
    /**
     * 生成二维码测试
     */
    @RequestMapping("/qr")
    public void testQRcode( HttpServletRequest request,
                            HttpServletResponse response) throws Exception{
        Map<EncodeHintType,Object> hints =  new HashMap<>();
    
        //设置纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //编码类型
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        String codeUrl="weixin://wxpay/bizpayurl?pr=hFq9fX6";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
        OutputStream out =  response.getOutputStream();
    
        MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
    }
    /**
     * 测试ip工具类
     */
    
    @RequestMapping("/ip")
    @ResponseBody
    public void httpResponse(HttpServletRequest request){
        String ipAddr = IpUtils.getIpAddr(request);
        System.out.println(ipAddr+"ip");
    }
    
}
