package com.ecpss.spring.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 * 集标题
 */
public class Episode implements Serializable{
    private Integer id;
    private String title; //集标题
    private Integer num;//第几集
    private String duration;//时长 分钟，单位
    private String coverImg;//封面图
    private Integer videoId;//视频id
    private String summary;//集概述
    private Date createTime;//创建时间
    private Integer chapterId;//章节主键id
    
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
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getCoverImg() {
        return coverImg;
    }
    
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getChapterId() {
        return chapterId;
    }
    
    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
}
