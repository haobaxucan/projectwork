package com.ecpss.controller.dto;

import com.ecpss.domain.OrderDetail;
import com.ecpss.domain.OrderMaster;
import lombok.Data;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
@Data
public class OrderDto extends OrderMaster{
    public List<OrderDetail> getOrderDetail;
}
