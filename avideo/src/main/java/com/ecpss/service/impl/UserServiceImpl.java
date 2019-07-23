package com.ecpss.service.impl;

import com.ecpss.spring.config.WechatConfig;
import com.ecpss.dao.UserDao;
import com.ecpss.spring.domain.User;
import com.ecpss.service.UserService;
import com.ecpss.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * 第二步：通过code获取access_token
 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
 * 参数	是否必须	说明
 * appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
 * secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
 * code	是	填写第一步获取的code参数
 * grant_type	是	填authorization_code\
 * ---正确的返回---------
 * <p>
 * {
 * "access_token":"ACCESS_TOKEN",
 * "expires_in":7200,
 * "refresh_token":"REFRESH_TOKEN",
 * "openid":"OPENID",
 * "scope":"SCOPE",
 * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
 * }
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private UserDao UserDao;
    
    @Override
    public User SaveWeChatUser(String code) {
        
        String access_url = String.format(WechatConfig.getAccess_token(), wechatConfig.getOpenAppId(), wechatConfig.getOpenAppId(), code);
        
        Map<String, Object> map = HttpUtils.doGet(access_url);//通过code获取access_token
        if (map == null && map.isEmpty()) {
            return null;
        }
        String access_token = (String) map.get("access_token");//object类型强转为string 类型
        String openId = (String) map.get("openid");
        User dbUser = UserDao.findByOpen(openId);
        if (dbUser != null) {
//            進行更新
            return dbUser;
        }
        
        
        String userInfo = String.format(WechatConfig.getUser_info(), access_token, openId);
        Map<String, Object> userMap = HttpUtils.doGet(userInfo);
        if (userMap == null && userMap.isEmpty()) {
            return null;
        }
        String nickname = (String) map.get("nickname");//object类型强转为string 类型
        try {
            //nickname 乱码 ，所以进行转码
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Double sexTemp = (Double) map.get("sex");
        int sex = sexTemp.intValue();//Double 类型的sex 转化为int
        /**
         * 封装User对象
         */
        User user = new User();
        user.setName(nickname);
        user.setSex(sex);
        user.setCreateTime(new Date());
//        ------这里调用dao 层保存用户信息-----
        UserDao.save(user);
        return user;
        
    }
    
    
    /**
     * access_token	接口调用凭证
     expires_in	access_token接口调用凭证超时时间，单位（秒）
     refresh_token	用户刷新access_token
     openid	授权用户唯一标识
     scope	用户授权的作用域，使用逗号（,）分隔
     */
    
}
