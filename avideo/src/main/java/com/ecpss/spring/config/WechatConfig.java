package com.ecpss.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by xc on 2019/6/18.
 */
@Configuration
@PropertySource("classpath:META-INF/context.properties")
public class WechatConfig {
    
    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
    /**
     * 公众号appid
     */
    @Value("${wxpay.appid}")
    private String appId;
    /**
     * 公众号秘钥
     */
    @Value("${wxpay.appsecret}")
    private String appSecret;
    
    /**
     *开放公众号appid
     */
    @Value("${wxopen.appid}")
    private String openAppId;
    /**
     *开放公众号秘钥
     */
    
    @Value("${wxopen.appsecret}")
    private String openAppSecret;
    /**
     * 回调地址
     */
    @Value("${wxopen.redirect_url}")
    private String openDirectUrl;
    
    /**
     * 开放平台获取access地址
     * @return
     */
    public   final static String access_token="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    
    /**
     *
     * 获取用户信息
     */
    private final static String user_info="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
    
    
    /**
     * 商户号id
     */
    @Value("${wxpay.mer_id}")
    private String mchId;
    
    
    /**
     * 支付key
     */
    @Value("${wxpay.key}")
    private String key;
    
    /**
     * 微信支付回调url
     */
    @Value("${wxpay.callback}")
    private String payCallbackUrl;
    
    /**
     * 统一下单地址
     * @return
     */
    private static final String UNIFIED_ORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }
    
    public String getMchId() {
        return mchId;
    }
    
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getPayCallbackUrl() {
        return payCallbackUrl;
    }
    
    public void setPayCallbackUrl(String payCallbackUrl) {
        this.payCallbackUrl = payCallbackUrl;
    }
    
    public static String getUser_info() {
        return user_info;
    }
    
    public static String getAccess_token() {
        return access_token;
    }
    
    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getAppSecret() {
        return appSecret;
    }
    
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
    public String getOpenAppId() {
        return openAppId;
    }
    
    public void setOpenAppId(String openAppId) {
        this.openAppId = openAppId;
    }
    
    public String getOpenAppSecret() {
        return openAppSecret;
    }
    
    public void setOpenAppSecret(String openAppSecret) {
        this.openAppSecret = openAppSecret;
    }
    
    public String getOpenDirectUrl() {
        return openDirectUrl;
    }
    
    public void setOpenDirectUrl(String openDirectUrl) {
        this.openDirectUrl = openDirectUrl;
    }
    
    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }
}
