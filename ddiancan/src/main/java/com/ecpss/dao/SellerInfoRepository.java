package com.ecpss.dao;

import com.ecpss.spring.domain.SellerInfo;

/**
 * Created by xc on 2019/6/26.
 */
public interface SellerInfoRepository {
    SellerInfo findByOpenid(String openid);
}
