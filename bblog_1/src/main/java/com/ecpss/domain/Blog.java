package com.ecpss.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
//    多的一方维护1 的一方
    private Integer user_id;
    
    private Integer Type_id;
    
    private Integer Tag_id;
    
    private String title;
    
 
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    @Column(columnDefinition="int default 0")
    private boolean published;
    @Basic
//    @Column(columnDefinition="int default 0")
    private boolean recommend;//也行--但是最好指定数据库的类型
    private String description;
    
    @Column(columnDefinition="timestamp",insertable=false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @Column(columnDefinition="int default 0")
    private boolean kuozhan;
    @Column(columnDefinition="varchar(255) default 'aa'")
    private String test;
    
    
}
