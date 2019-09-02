package com.ecpss.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by xc on 2019/8/29.
 */
@Data
@NoArgsConstructor
public class Page <T>{
    private List<T> users;
    
    private int pageNo;
    private int pageSize;
    private int totalSize;
    

}
