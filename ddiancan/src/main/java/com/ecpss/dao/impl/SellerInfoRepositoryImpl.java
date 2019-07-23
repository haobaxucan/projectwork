package com.ecpss.dao.impl;

import com.ecpss.dao.SellerInfoRepository;
import com.ecpss.spring.domain.SellerInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by xc on 2019/6/26.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class SellerInfoRepositoryImpl implements SellerInfoRepository{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public SellerInfo findByOpenid(String openid) {
        String hql="from SellerInfo s where s.openid=?1";
        Query query = em.createQuery(hql).setParameter(1, openid);
        return (SellerInfo) query.getResultList().stream().findFirst().orElse(null);
    }
}
