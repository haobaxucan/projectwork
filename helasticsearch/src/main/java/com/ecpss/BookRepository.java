package com.ecpss;

import com.ecpss.book.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by xc on 2019/9/2.
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
//    支持自定义方法

    public List<Book> findByNameLike(String name);
//    @Query("from Book  where name =:name")
    Book findByName(String name);
    
    Book findBooKById(Integer id);
  
//   public Book findNative(Integer id);
    
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"name\" : \"?0\"}}}}")
//    List<Book> findByName(String name);
//
}
