package com.ecpss.service;

import com.ecpss.controller.dto.OrderDto;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public interface OrderService {
   
    OrderDto createOrder(OrderDto orderDto);
    
    OrderDto getByOne(Integer OrderId);
    
    List<OrderDto> findList();
    
    OrderDto cancleOrder(OrderDto orderDto);
    
    OrderDto finish(OrderDto orderDto);
    
    OrderDto pay(OrderDto orderDto);
    
}
