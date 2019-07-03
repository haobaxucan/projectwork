package com.ecpss.domain.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by xc on 2019/6/25.
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
}
