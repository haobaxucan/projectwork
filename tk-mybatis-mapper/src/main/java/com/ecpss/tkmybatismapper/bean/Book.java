package com.ecpss.tkmybatismapper.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.00
 * @date 2019/10/11
 */
@Data
public class Book {
    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer uid;
    private String name;
    private BigDecimal price;
    private Date birthday;



}
