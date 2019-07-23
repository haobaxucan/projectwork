package com.ecpss.dao;

import com.ecpss.spring.domain.OrderDetail;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public interface OrderDetailRepository {
    List<OrderDetail> findByOrderId(Integer orderId);
    
    public void saveOrUpddate(OrderDetail orderDetail);
}
