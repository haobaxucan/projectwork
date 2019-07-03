package com.ecpss.service;

import com.ecpss.controller.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;

/**
 * Created by xc on 2019/6/27.
 */
public interface PayService {
    PayResponse create(OrderDto orderDto);
}
