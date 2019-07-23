package com.ecpss.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xc on 2019/6/26.
 */
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;///商品id
    private String productName;
    private BigDecimal money;//商品价格
   
    
    private Integer store;//库存
    
    private String descr;//描述
    
    /** 小图. */
    private String productIcon;
    
    /** 类目编号. */
    private Integer categoryType;
    
    private Date createTime;
    
    private Date updateTime;
    /** 状态, 0正常1下架. */
    private Integer productStatus;
    
    private BigDecimal deca;//扩展字段
}
