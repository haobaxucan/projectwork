package com.ecpss.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_master") //在创建订单表的时候出现了两次语法错误，order是关键字，如果可以创建，就是关键字出现了
public class OrderMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    /** 买家名字. */
    private String buyerName;
    
    /** 买家手机号. */
    private String buyerPhone;
    
    /** 买家地址. */
    private String buyerAddress;
    
    /** 买家微信Openid. */
    private String buyerOpenid;
    /** 创建时间. */
    private Date createTime;
    
    /** 更新时间. */
    private Date updateTime;
    
    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;
    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;
    
    /** 订单总金额. */
    private BigDecimal orderAmount;
}
