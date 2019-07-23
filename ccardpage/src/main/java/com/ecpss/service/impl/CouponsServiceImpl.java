package com.ecpss.service.impl;

import com.ecpss.dao.CouponsDao;
import com.ecpss.spring.domain.Coupons;
import com.ecpss.service.CouponsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xc on 2019/6/25.
 */
@Slf4j
@Service
public class CouponsServiceImpl implements CouponsService{
    @Autowired
    private CouponsDao couponsDao;
    @Override
    public boolean dropCoupons(Coupons coupons) {
        if(null==coupons){
         return false;}
        couponsDao.Save(coupons);
        return true;
    }
}
