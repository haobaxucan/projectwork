package com.ecpss.spring.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 * 章节实体类
 */
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    private Integer videoId;//视频主键
    private String title; //章节名称
    private Integer ordered;//章节顺序
    @Temporal(value = TemporalType.DATE)
    private Date createTime;//创建时间
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getOrdered() {
        return ordered;
    }
    
    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
