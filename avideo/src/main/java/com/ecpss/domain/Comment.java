package com.ecpss.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 */

@Entity
@Table(name = "comment")
public class Comment {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    private String content;//内容
    private Integer userId;
    private String headImg;//用户头像
    private String name;//昵称
    private double point; //评分，10分满分
    private Integer up;//点赞数
    @Temporal(value = TemporalType.DATE)
    private Date createTime;
    private Integer orderId;//订单id
    private Integer videoId;//视频id
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getHeadImg() {
        return headImg;
    }
    
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPoint() {
        return point;
    }
    
    public void setPoint(double point) {
        this.point = point;
    }
    
    public Integer getUp() {
        return up;
    }
    
    public void setUp(Integer up) {
        this.up = up;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
