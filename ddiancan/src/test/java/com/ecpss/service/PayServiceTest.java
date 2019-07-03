package com.ecpss.service;

import com.ecpss.controller.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xc on 2019/6/27.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayServiceTest {
    @Autowired
    public PayService payService;
    @Autowired
    OrderService orderService;
    @Test
    public void create() throws Exception {
        OrderDto orderDto = orderService.getByOne(1);
        payService.create(orderDto);
    }
    
}