package com.ecpss.spring.domain.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

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
