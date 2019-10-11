package com.ecpss.wx_public_number.controller;

import com.ecpss.wx_public_number.utils.XmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

@Controller
public class WechatController {
    static final String TOKEN = "wx4";

    @ResponseBody
    @RequestMapping(value = "/wx/portal/wx30de7e8ac3923ce2",method = RequestMethod.GET)
    public String getMsg(HttpServletRequest request) throws Exception {
        System.out.println("get");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(signature + "---" + timestamp + "---" + nonce + "---" + echostr);
        /**
         * 1）将token、timestamp、nonce三个参数进行字典序排序
         * 2）将三个参数字符串拼接成一个字符串进行sha1加密
         * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         *
         */
        String s[] = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(s);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s[0]).append(s[1]).append(s[2]);
        String s1 = sha1(stringBuilder.toString());
        System.out.println(signature.equals(s1));//true
        if(signature.equals(s1)){
            return echostr;
        }

        return "aa";
    }

    String sha1(String string) throws Exception {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(string.getBytes("UTF-8"));
        byte[] md = mdTemp.digest();
        int j = md.length;
        char buf[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }
    @RequestMapping(value = "/wx",method = RequestMethod.POST)
    @ResponseBody
    public String msgPost(HttpServletRequest request)throws Exception{
//      解析xml输入流
        InputStream inputStream = request.getInputStream();
        byte b[]=new byte[1024];
        int len = 0;
        StringBuilder sb=new StringBuilder();
        while ((len=inputStream.read(b))!=-1){
            sb.append(new String(b,0,len));
        }
        Map<String, String> map = XmlUtil.xmlToMap(sb.toString());
        map.forEach((x,y)->{
            System.out.println(x+"-"+y); //还可以发送语音，位置，视频消息，图片消息
        });
//       xcde
//        回复消息
        String respXml="<xml><ToUserName>" +
                "<![CDATA["+map.get("ToUserName")+"]]>" +
                "</ToUserName><FromUserName><![CDATA["+map.get("FromUserName")+"]]>" +
                "</FromUserName><CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType><Content><![CDATA[xc]]></Content></xml>";
//        消息的回复

        return respXml;
    }








//    @RequestMapping(value = "/wx",method = RequestMethod.POST)
//    @ResponseBody
//    public String msgPost(HttpServletRequest request)throws Exception{
//        System.out.println("post---");
//        InputStream inputStream = request.getInputStream();
//        byte b[]=new byte[1024];
//        int len = 0;
//        StringBuilder sb=new StringBuilder();
//        while ((len=inputStream.read(b))!=-1){
//            sb.append(new String(b,0,len));
//        }
//        System.out.println(sb.toString());
//        return "bb";
//    }
}
