package com.ecpss.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by xc on 2019/6/26.
 */
@Data
@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** 商品名称. */
    private String productName;
    
    /** 商品单价. */
    @JsonProperty
    private BigDecimal productPrice;
    
    /** 商品数量. */
    private Integer productQuantity;
    
    /** 商品小图. */
    private String productIcon;
    
    private Integer orderId;//订单id
    private Integer productid;//产品id

}
