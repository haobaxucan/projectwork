package com.ecpss.tkmybatis.controller.json;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @version 1.00
 * @date 2019/10/21
 */
@Data
@Accessors(chain = true)
public class MscAccountInfoDTO implements Serializable {
    private String openId;
    private String messageContent;
}
