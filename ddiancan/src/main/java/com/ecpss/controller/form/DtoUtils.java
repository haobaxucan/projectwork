package com.ecpss.controller.form;

import com.ecpss.controller.dto.OrderDto;

/**
 * Created by xc on 2019/6/27.
 */
public class DtoUtils {
    
    public static OrderDto convertOrderDto(OrderForm orderForm){
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderDto.getBuyerPhone());
//        orderForm.getItems();
        return orderDto;
    }
}
