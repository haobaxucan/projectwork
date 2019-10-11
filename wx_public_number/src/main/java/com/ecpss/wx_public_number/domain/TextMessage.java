package com.ecpss.wx_public_number.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage {//子类不能继承父类的私有方法和属性
    /**
     * Content-411
     * CreateTime-1570781466
     * ToUserName-gh_074283750c77
     * FromUserName-oFm7l1BFG8jNNV1xgwNwP0xM2nHQ
     * MsgType-text
     * MsgId-22488185533974295
     */
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

    public TextMessage(Map<String,String> map,String content){
        this.ToUserName=map.get("ToUserName");
        this.FromUserName=map.get("FromUserName");
        this.MsgType="text";
        this.Content=content;
    }



}