package com.ecpss.tkmybatis.controller.json;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * 营销消息发送任务
 */
@Data
@Accessors(chain = true)
public class MscSendTaskEntity  {
    private static final long serialVersionUID = 1L;
    private String status;  // 发送状态
    private Timestamp planSendTime;// 计划发送时间
    private String sender; // 发送者
    private String senderName; // 发送者姓名
    private String receiver; // 接收者
    private String receiverName; // 接收者姓名/群名
    private String receiverPhone; // 接收者手机号
    private String receiverUserId; // 接收者用户ID
    private String receiverType; // 接收者类型，个人/群
    private String contentType; // 消息类型，短信/文字/模板/图文/url/站内信
    private Integer tryTimes; // 重试次数
    private String memo; // 备注
    private String content; // 消息内容
    private String userGroup; // 用户群体，如SELLER/BUYER
    private String module; // 功能模块
    private String referType; // 关联信息类型，如合同、订单、商品、报价等
    private String referNo; // 关联信息编号
    private String templateCode; //模板码 选择发送消息的模板
    private Integer customerId; //客户id


    public MscSendTaskEntity() {

    }

}