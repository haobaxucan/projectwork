package com.ecpss.tkmybatis.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 1.00
 * @date 2019/9/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userd1")
public class TestUser {
    @Id
    private Integer id;
    private String name;
    private String addx;
}
