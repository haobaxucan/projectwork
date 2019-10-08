package com.ecpss.tkmybatismapper.Base.test.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@AllArgsConstructor
public enum  CityStatusEnums {
    INIT("INIT","初始"),
    ERROR("ERROR","出错"),
    SUCCESS("SUCCESS","初始");
    @Getter
    private String code;
    @Getter
    private String desc;

}
