package com.ecpss.spring.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 */
@Entity
@Table(name = "video_order")
public class VideoOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String openid;//用户表示
    
    private String outTradeNo;//订单唯一标识
    /**
     * 0表示未支付，1表示已经支付
     */
    private Integer state;
    @Temporal(value = TemporalType.DATE)
    private java.util.Date createTime;//订单生成时间
    @Temporal(value = TemporalType.DATE)
    private java.util.Date notifyTime;//订单回调时间
    /**
     *分为单位
     */
    private Integer totalFee;
    private String nickname;//微信昵称
    
    private String headImg;//微信头像
    private Integer videoId;//视频主键
    private String videoTitle;//视频名称
    private String videoImg;//视频图片
    private Integer userId;//用户id
    private String ip;//用户ip地址
    private Integer del;//0表示未删除，1表示已经删除
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    public String getOutTradeNo() {
        return outTradeNo;
    }
    
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getNotifyTime() {
        return notifyTime;
    }
    
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }
    
    public Integer getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getHeadImg() {
        return headImg;
    }
    
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
    
    public String getVideoTitle() {
        return videoTitle;
    }
    
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
    
    public String getVideoImg() {
        return videoImg;
    }
    
    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Integer getDel() {
        return del;
    }
    
    public void setDel(Integer del) {
        this.del = del;
    }
}
