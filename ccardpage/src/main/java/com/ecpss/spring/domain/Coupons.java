package com.ecpss.spring.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/6/25.
 */

@Table(name = "coupons")
@Entity
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//默认使用auto 支持mysql  序列才支持oracle
    private long id;
    
    /** 所属商户 id */
   
    private Long mer_id;
    
    /** 优惠券标题 */
    private String title;
    
    /** 优惠券摘要 */
    private String summary;
    
    /** 优惠券的详细信息 */
    private String descri;
    
    /** 最大个数限制 */
    private Long res;
    
    /** 优惠券是否有 Token, 用于商户核销 */
    private Boolean hasToken; // token 存储于 Redis Set 中, 每次领取从 Redis 中获取
    
    /** 优惠券背景色 */
    private Integer background;
    
    /** 优惠券开始时间 */
    private Date start;
    
    /** 优惠券结束时间 */
    @Basic
    private Date end;
    
   
}
