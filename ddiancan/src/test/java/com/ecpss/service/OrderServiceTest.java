package com.ecpss.service;

import com.ecpss.controller.dto.OrderDto;
import com.ecpss.domain.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xc on 2019/6/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    
    /**
     * 创建订单
     * @throws Exception
     */
    @Test
    public void createOrder() throws Exception {
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName("xucan");
        orderDto.setPayStatus(0);
        orderDto.setBuyerPhone("1223");
        orderDto.setOrderAmount(new BigDecimal(195));
        List<OrderDetail> orderDetails=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderId(2);
        orderDetail.setProductid(2);
        orderDetail.setProductPrice(new BigDecimal(123));
        orderDetails.add(orderDetail);
        orderDetail.setProductQuantity(4);
        
        orderDto.setGetOrderDetail(orderDetails);
        orderService.createOrder(orderDto);
    
    }
    
    @Test
    public void getByOne() throws Exception {
    }
    
    @Test
    public void findList() throws Exception {
    }
    
    @Test
    public void cancleOrder() throws Exception {
        OrderDto dto = orderService.getByOne(1);
        
    }
    
    @Test
    public void finish() throws Exception {
    }
    
    @Test
    public void pay() throws Exception {
    }
    
}