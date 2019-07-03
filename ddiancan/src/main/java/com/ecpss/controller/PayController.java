package com.ecpss.controller;

import com.ecpss.controller.dto.OrderDto;
import com.ecpss.service.OrderService;
import com.ecpss.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by xc on 2019/6/27.
 */
@Controller
public class PayController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PayService payService;
    
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1. 查询订单
//        OrderDto orderDTO = orderService.getByOne(orderId);
//        if (orderDTO == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
        //2. 发起支付
        OrderDto orderDto=new OrderDto();
        payService.create(orderDto);
        
//        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        
        return new ModelAndView("pay/create", map);
    }

}
