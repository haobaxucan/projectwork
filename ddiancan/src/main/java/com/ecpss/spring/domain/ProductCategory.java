package com.ecpss.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/6/26.
 */
@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer categoryNo;// 类目标号
    
    private Date createTime;//创建时间
    
    private Date updateTime;//更新时间

}
