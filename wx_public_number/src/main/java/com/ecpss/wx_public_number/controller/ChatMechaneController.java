package com.ecpss.wx_public_number.controller;

import com.ecpss.wx_public_number.utils.JuheLiaoTianUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChatMechaneController {
    public static final String APPKEY = "d430f593d5212f3af755989b040457ab";

    @RequestMapping("/msg")
    public String getMsg(String msg) throws Exception{
        String result = null;
        String url = "http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info", msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype", "");//返回的数据的格式，json或xml，默认为json
        params.put("loc", "");//地点，如北京中关村
        params.put("lon", "");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat", "");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid", "");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联

        result = JuheLiaoTianUtil.net(url, params, "GET");
        System.out.println(result);
//{"reason":"此appkey不适用于当前接口","result":null,"error_code":10015}
        return result;
    }

}
