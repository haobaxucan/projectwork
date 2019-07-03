package com.ecpss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/6/25.
 * 用户领取优惠券的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_coupons")
@Entity
public class UserCoupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /** 用户 id */
    @Column(name = "user_id")
    private Long userId;
    
    /** pass 在 HBase 中的 RowKey */
    private String rowKey;
    
    /** PassTemplate 在 HBase 中的 RowKey */
    private String templateId;
    
    /** 优惠券 token, 有可能是 null, 则填充 -1 */
    private String token;
    
    /** 领取日期 */
    private Date assignedDate;
    
    /** 消费日期, 不为空代表已经被消费了 */
    private Date conDate;
    
}
