package com.ecpss.spring.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xc on 2019/6/18.
 * 用户
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column
    private String openid;//微信openid
    private String name;//昵称
    private String headImg;//昵称
    private String phone;//手机号
    private String sign;//用户签名
    private Integer sex;//0表示女，1表示男
    private String city;//城市
    @Temporal(value = TemporalType.DATE)
    private Date createTime;//创建时间
    
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getHeadImg() {
        return headImg;
    }
    
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public Integer getSex() {
        return sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
