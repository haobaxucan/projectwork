package com.ecpss.spring.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xc on 2019/6/26.
 */

@Data
@Entity
@Table(name = "seller")
public class SellerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    @Column(name = "PAss_W0rd")
    private String password;
    @Basic
    @Column
    private String openid;
}
