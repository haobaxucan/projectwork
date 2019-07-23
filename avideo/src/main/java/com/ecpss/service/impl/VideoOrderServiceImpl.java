package com.ecpss.service.impl;

import com.ecpss.spring.config.WechatConfig;
import com.ecpss.dao.UserDao;
import com.ecpss.dao.VideoDao;
import com.ecpss.dao.VideoOrderDao;
import com.ecpss.spring.domain.User;
import com.ecpss.spring.domain.Video;
import com.ecpss.spring.domain.VideoOrder;
import com.ecpss.dto.VideoOrderDto;
import com.ecpss.service.VideoOrderService;
import com.ecpss.utils.CommonUtils;
import com.ecpss.utils.HttpUtils;
import com.ecpss.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xc on 2019/6/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    private VideoOrderDao videoOrderDao;
    @Autowired
    private VideoDao VideoDao;
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<VideoOrder> findAll() {
        return videoOrderDao.findAll();
    }
    
    @Override
    public VideoOrder findById(Integer id) {
        return videoOrderDao.findById(id);
    }
    
    @Override
    public void delete(Integer id) {
        videoOrderDao.delete(id);
    }
    
    @Override
    public String saveOrUpdate(VideoOrderDto videoOrderDto) {
        //查找视频信息
        Video video = VideoDao.findById(videoOrderDto.getVideoId());
        
        //查找用户信息
        User user = userDao.findById(videoOrderDto.getUserId());
        
        /**
         * 生成订单
         */
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setCreateTime(new Date());
        videoOrder.setUserId(user.getId());
        videoOrder.setNickname(user.getName());
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());//生成流水号
        videoOrder.setIp(videoOrderDto.getIp());
        
        
        videoOrderDao.saveOrUpdate(videoOrder);
        /**
         *
         * 统一下单 获取codeUrl
         *
         */
         String codeUrl = UnionOrder(videoOrder);
         if(null==codeUrl){
             throw new NullPointerException();
         }
        
        return null;
    }
    
    /**
     * 生成签名
     * 统一下单
     */
    public String UnionOrder(VideoOrder videoOrder) {
        SortedMap<String, String> params = new TreeMap<>();
        params.put("appid", wechatConfig.getAppId());  //公众账号ID
        params.put("mch_id", wechatConfig.getMchId());  //商户号
        params.put("nonce_str", CommonUtils.generateUUID());//随机字符串
        params.put("body", "title");//商品描述
        params.put("out_trade_no", videoOrder.getOutTradeNo());//out_trade_no
        params.put("total_fee", String.valueOf(videoOrder.getTotalFee()));//标价金额
        params.put("spbill_create_ip", videoOrder.getIp());//终端IP
        params.put("notify_url", wechatConfig.getPayCallbackUrl());//通知地址
        params.put("trade_type", "NATIVE");//交易类型
        // 生成签名
        String sign = WXPayUtil.createSign(params, wechatConfig.getKey());
        params.put("sign", sign);
        //map 转成xml
        String xml = null;
        try {
            xml = WXPayUtil.mapToXml(params);
        } catch (Exception e) {
            System.out.println("xml转换异常");
            e.printStackTrace();
        }
        //统一下单
        String orderStr = HttpUtils.doPost(WechatConfig.getUnifiedOrderUrl(),xml,4000);
        if(null == orderStr) {
            return null;
        }
    
        Map<String, String> unifiedOrderMap = null;
        try {
            unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(unifiedOrderMap.toString());
        if(unifiedOrderMap != null) {
            return unifiedOrderMap.get("code_url");
        }
    
        return null;
    }
    
    
    

    
}
