package com.ecpss.dao.impl;

import com.ecpss.dao.CouponsDao;
import com.ecpss.spring.domain.Coupons;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by xc on 2019/6/25.
 */
@Repository
public class CouponsDaoImpl implements CouponsDao{
    @PersistenceContext
    EntityManager entityManager;
    
    public void Save(Coupons coupons){
        entityManager.persist(coupons);
    }
}
