package com.ecpss.tkmybatismapper.Base.test.entity;

import com.ecpss.tkmybatismapper.Base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@Data
@Accessors(chain = true)
@Table(name = "city")
public class CityEntity extends BaseEntity {
    @Column(name = "name" )
    private String name;
    private String addx;
    private String status;
    @Column(name = "plantime")
    private Timestamp plantime;
    @Column(name = "utime")
    private Date utime;
}
