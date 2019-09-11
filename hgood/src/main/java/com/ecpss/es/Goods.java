package com.ecpss.es;

import io.searchbox.annotations.JestId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Goods {
    //    表示这是一个主键
    @JestId
    private Integer id;
    private String name;
    private String desc;
    private double price;
}
