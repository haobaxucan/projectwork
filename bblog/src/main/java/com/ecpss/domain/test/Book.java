package com.ecpss.domain.test;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by xc on 2019/6/25.
 */
@Document(type = "book",indexName = "book")
public class Book {
    private Integer id;
    private String name;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
