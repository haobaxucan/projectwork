package com.ecpss.wx_public_number.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version 1.00
 * @date 2019/10/11
 */
@Data
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;


}
