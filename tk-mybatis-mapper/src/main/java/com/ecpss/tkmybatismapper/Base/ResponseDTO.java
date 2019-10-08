package com.ecpss.tkmybatismapper.Base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseDTO<T> implements Serializable {
    private int respCode;
    private String respMsg;
    private T data;
    private List<T> dataList;

    private int pageNo;
    private int pageSize;
    private int totalCount;

    public ResponseDTO() {
        setRespCode(200);
        setRespMsg("");
    }
}
