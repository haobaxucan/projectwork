package com.ecpss.dao.impl;

import com.ecpss.dao.OrderDetailRepository;
import com.ecpss.spring.domain.OrderDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class OrderDetailRepositoryImpl implements OrderDetailRepository{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        String hql="from OrderDetail o where o.orderId=?1";
        Query query = em.createQuery(hql).setParameter(1, orderId);
    
        return query.getResultList();
    }
    
    @Override
    public void saveOrUpddate(OrderDetail orderDetail) {
        if(orderDetail.getId()==null){
            em.persist(orderDetail);
        }
        em.merge(orderDetail);
    
    }
}
