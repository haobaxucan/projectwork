package com.ecpss.controller.dto;

import lombok.Data;

/**
 * Created by xc on 2019/6/26.
 */
@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T date;
}
