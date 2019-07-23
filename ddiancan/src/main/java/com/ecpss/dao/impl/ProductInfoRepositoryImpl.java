package com.ecpss.dao.impl;

import com.ecpss.dao.ProductInfoRepository;
import com.ecpss.spring.domain.ProductInfo;
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
public class ProductInfoRepositoryImpl implements ProductInfoRepository{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void saveOrUpdate(ProductInfo productInfo) {
        if(productInfo.getId()==null){
            em.persist(productInfo);
        }
        em.merge(productInfo);
        
    }
    
    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        String hql="from ProductInfo p where p.productStatus=?1";
        Query query = em.createQuery(hql).setParameter(1, productStatus);
        
        return query.getResultList();
    }
    
    @Override
    public ProductInfo findOne(Integer id) {
        String hql="from ProductInfo p where p.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        return (ProductInfo) query.getResultList().stream().findFirst().orElse(null);
    }
  
}
