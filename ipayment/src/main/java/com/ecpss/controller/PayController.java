package com.ecpss.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ecpss.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by xc on 2019/9/10.
 */
@Slf4j
@Controller
public class PayController {
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @Autowired
    AlipayClient alipayClient;
    
    /**
     * 支付提交
     *
     * @param orderId
     * @param total
     * @return
     */
    @RequestMapping("/alipay/submit")
    @ResponseBody
    public String alipay(String orderId, BigDecimal total) {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(AlipayConfig.return_payment_url);//同步回调地址商户自己的配置
        alipayRequest.setNotifyUrl(AlipayConfig.notify_payment_url);
        
        log.info("开始打印日志{},{}", orderId, total);
        String form = "";
        Map<String, Object> map = new HashMap<>();
    
        map.put("out_trade_no", "10eqr15311f0101");
        map.put("product_code", "FAST_INSTANT_TRADE_PAY");//写死
        map.put("total_amount", total);
        map.put("subject", new Random(10).nextInt());
        String param = JSONObject.toJSONString(map);
        alipayRequest.setBizContent(param);
    
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

//            保存订单信息
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        
        return form;
    }
    
    /**
     * 同步回调地址---- 商户自己的url （要配置运行的端口号）
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("alipay/callback/return")
    @ResponseBody
    public String aliPayCallBackReturn(HttpServletRequest request, ModelMap modelMap) {
        
        // 回调请求中获取支付宝参数
        String sign = request.getParameter("sign");
        String trade_no = request.getParameter("trade_no");
        String out_trade_no = request.getParameter("out_trade_no");
        String trade_status = request.getParameter("trade_status");
        String total_amount = request.getParameter("total_amount");
        String subject = request.getParameter("subject");
        String call_back_content = request.getQueryString();
    
        // 通过支付宝的paramsMap进行签名验证，2.0版本的接口将paramsMap参数去掉了，导致同步请求没法验签
        if (StringUtils.isEmpty(sign)) {
            // 验签成功
            // 更新用户的支付状态
        }
    
    
        return "finish";
    }
    
    /**
     * 异步通知  直接复制demo
     */
    @RequestMapping("alipay/callback/notify")
    @ResponseBody
    public String aliPayCallBackNotify(HttpServletRequest request, ModelMap modelMap) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }
    
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
    
        //——请在这里编写您的程序（以下代码仅作参考）——
	
	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {   //验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
    
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
    
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
    
            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
        
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
        
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
    
            System.out.println("succ");
    
        } else {//验证失败
            System.out.println("fail");
    
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        return "";
    
    }
    

    
}
