package com.ecpss.dao;

import com.ecpss.domain.OrderMaster;

/**
 * Created by xc on 2019/6/26.
 */
public interface OrderMasterRepository {
     void saveOrUpdate(OrderMaster orderMaster);
     
     public OrderMaster getById(Integer orderId);
}
