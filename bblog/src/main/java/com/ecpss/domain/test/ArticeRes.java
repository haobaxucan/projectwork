package com.ecpss.domain.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by xc on 2019/6/25.
 */

//@Transactional
public interface ArticeRes extends ElasticsearchRepository<Article,Integer>{


//    @PersistenceContext
//    EntityManager em;
//
//    public void save(Article article){
//        em.persist(article);
//    }
//
//    public Page<Article> findbyauthor(String author, Pageable pageable){
//        String hql="from Article a where a.author=?1";
//        Query query = em.createQuery(hql).setParameter(1, author);
//        return (Page<Article>) query.getResultList();
//    }
}
