package com.ecpss.dao.impl;

import com.ecpss.dao.TypeDao;
import com.ecpss.domain.BlogType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by xc on 2019/8/26.
 */
@Slf4j
@Transactional
@Repository
public class TypeDaoImpl extends DefaultDaoImpl implements TypeDao {
    @Override
    public void saveOrUpdate(BlogType type) {
        if (type.getId() == null) {
            log.info("这是增加 id={}", type.getId());
            em.persist(type);
        } else {
            log.info("这是修改 id={}", type.getId());
            em.merge(type);
        }
        
    }
    
    @Override
    public BlogType findType(Integer id) {
        String hql = "from BlogType t where t.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        BlogType type = (BlogType) query.getResultList().stream().findFirst().orElse(null);
        return type;
    }
    
    @Override
    public BlogType findType(String name) {
        String hql = "from BlogType t where t.name=?1";
        Query query = em.createQuery(hql).setParameter(1, name);
        BlogType type = (BlogType) query.getResultList().stream().findFirst().orElse(null);
        return type;
    }
    
    @Override
    public List<BlogType> findAll() {
        String hql = "from BlogType t";
        Query query = em.createQuery(hql);
        return query.getResultList();
    }
    
    @Override
    public void delete(Integer id) {
        String hql = "delete from BlogType t where t.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        query.executeUpdate();
    }
}
