package com.ecpss.wx_public_number.domain;

import lombok.Data;

import java.util.Map;

/**
 * @version 1.00
 * @date 2019/10/11
 */
@Data
public class ImageMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String MediaId;

    public ImageMessage(Map<String,String> map, String MediaId){
        this.ToUserName=map.get("ToUserName");
        this.FromUserName=map.get("FromUserName");
        this.MsgType="text";
        this.MediaId=MediaId;
    }

}
