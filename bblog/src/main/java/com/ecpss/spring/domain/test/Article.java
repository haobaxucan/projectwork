package com.ecpss.spring.domain.test;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * Created by xc on 2019/6/24.
 */
@Document(indexName = "article",type = "article")
public class Article {
    @Id  //表明这是一个主键
    private Integer id;
    private String title;
    private String author;
    
    public Integer getId() {
        return id;
    }
    
    public Article() {
    }
    
    public Article(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
