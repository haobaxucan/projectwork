package com.ecpss.dao.impl;

import com.ecpss.dao.CategoryRepository;
import com.ecpss.domain.ProductCategory;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public ProductCategory getById(Integer id) {
    
        String hql="from ProductCategory p where p.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        return (ProductCategory) query.getResultList().stream().findFirst().orElse(null);
    }
    
    @Override
    public List<ProductCategory> getList() {
        String hql="from ProductCategory";
        Query query = em.createQuery(hql);
        return query.getResultList();
    }
    
    @Override
    public void saveOrUpddate(ProductCategory productCategory) {
        if(productCategory.getId()==null){
            em.persist(productCategory);
        }
        em.merge(productCategory);
    }
}
