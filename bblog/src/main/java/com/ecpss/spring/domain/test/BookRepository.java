package com.ecpss.spring.domain.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by xc on 2019/6/25.
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
}
