package com.ecpss.dao.impl;

import com.ecpss.dao.OrderMasterRepository;
import com.ecpss.spring.domain.OrderMaster;
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
public class OrderMasterRepositoryImpl implements OrderMasterRepository{
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void saveOrUpdate(OrderMaster orderMaster) {
    
        if(orderMaster.getOrder_id()==null){
            em.persist(orderMaster);
        }
        em.merge(orderMaster);
    }
    
    @Override
    public OrderMaster getById(Integer orderId) {
        String hql="from OrderMaster o where o.order_id=?1";
        Query query = em.createQuery(hql).setParameter(1, orderId);
        return (OrderMaster)query.getResultList().stream().findFirst().orElse(null);
    }
}
