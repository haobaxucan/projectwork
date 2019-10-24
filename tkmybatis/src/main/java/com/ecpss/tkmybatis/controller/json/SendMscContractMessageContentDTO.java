package com.ecpss.tkmybatis.controller.json;

import lombok.Data;

/**
 * @version 1.00
 * @date 2019/10/16
 */
@Data
public class SendMscContractMessageContentDTO {

    private String id;
    private String contract_image; //合同图片地址
    private String good_name;  //商品名称
    private String good_price; //商品单价
    private String good_num; //买家的购买数量



}
