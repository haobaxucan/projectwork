package com.ecpss.spring.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 *视频表
 *
 */
@Entity
@Table(name = "video")
public class Video implements Serializable{
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id; //id
    private String title; //标题
    private String summary; //概要
    private String coverImg; //图片
    private Integer viewNum; //观看数
    private Integer price; //价格
    private Date createTime;
    private Integer online;//是否在线 0表示为在线，1表示在线
    private Integer point;//评分
    
    /**
     * 一般情况下不需要全部参数构造函数
     * @return
     */
    
    public Integer getId() {
        return id;
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
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getCoverImg() {
        return coverImg;
    }
    
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    
    public Integer getViewNum() {
        return viewNum;
    }
    
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getOnline() {
        return online;
    }
    
    public void setOnline(Integer online) {
        this.online = online;
    }
    
    public Integer getPoint() {
        return point;
    }
    
    public void setPoint(Integer point) {
        this.point = point;
    }
}
