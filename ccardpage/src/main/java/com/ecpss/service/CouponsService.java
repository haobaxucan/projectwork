package com.ecpss.service;

import com.ecpss.spring.domain.Coupons;

/**
 * Created by xc on 2019/6/25.
 * 投放优惠券 passtemplate
 */
public interface CouponsService {
    
    boolean dropCoupons(Coupons coupons);
}
