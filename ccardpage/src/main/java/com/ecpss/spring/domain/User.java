package com.ecpss.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>User Object</h1>
 * Created by Qinyi.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    /** 用户 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//默认使用auto 支持mysql  序列才支持oracle
    private Long id;

    /** 用户基本信息 */
//    private BaseInfo baseInfo;
    private String name;
    private Integer age;
    private String sex; //性别
    
    
    /** 用户额外信息 */
//    private OtherInfo otherInfo;

        private String phone;
        private String address;
}
