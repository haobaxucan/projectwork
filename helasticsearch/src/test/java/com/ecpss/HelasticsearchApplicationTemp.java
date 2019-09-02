package com.ecpss;

import com.ecpss.book.Book;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelasticsearchApplicationTemp {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    
    //	创建索引
    @Test
    public void createIndex() {
        // 1、直接用名称创建
//        elasticsearchTemplate.createIndex("user");
    
        elasticsearchTemplate.createIndex(Book.class);
    
    }
//添加数据
    @Test
    public void add() {
        
        Book book = new Book();
        book.setId("32");
        book.setName("chaoshen");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(book.getId())
                .withObject(book)
                .build();
        elasticsearchTemplate.index(indexQuery);

    }
    
    @Test
    public void query() {//但字符串查询
    
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("chaoshen22"))
                .withPageable(new PageRequest(0, 20))
                .build();
        List<Book> list = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        System.out.println(list.size());
    
    }
    //模糊查询
    @Test
    public void queryById() {
        Pageable pageable = new PageRequest(0, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "chaoshen"))
                .withPageable(pageable)
                .build();
        List<Book> list = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        System.out.println(list.size());
    
    }
}
