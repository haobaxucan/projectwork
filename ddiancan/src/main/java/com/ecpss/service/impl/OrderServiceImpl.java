package com.ecpss.service.impl;

import com.ecpss.controller.dto.CartDto;
import com.ecpss.controller.dto.OrderDto;
import com.ecpss.dao.OrderDetailRepository;
import com.ecpss.dao.OrderMasterRepository;
import com.ecpss.dao.ProductInfoRepository;
import com.ecpss.spring.domain.OrderDetail;
import com.ecpss.spring.domain.OrderMaster;
import com.ecpss.spring.domain.ProductInfo;
import com.ecpss.enums.OrderStatus;
import com.ecpss.service.OrderService;
import com.ecpss.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by xc on 2019/6/26.
 */
@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMasterRepository masterRepository;
    @Autowired
    private ProductInfoRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;
    
    /**
     * 创建订单
     * 入库
     * 但是前面还有逻辑 代码
     *
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto createOrder(OrderDto orderDto) {//一笔订单有几笔明细
        int anInt = new Random(System.currentTimeMillis()).nextInt(1000);
//        查询商品（库存 数量）
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        List<OrderDetail> detailList = orderDto.getGetOrderDetail();
        for (OrderDetail detail : detailList) {
            ProductInfo productInfo = repository.findOne(detail.getProductid());
            if (null == productInfo) {
                throw new RuntimeException("商品不存在");
                
            }
            //  算总价
            
            amount = detail.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(amount);
            
            //        写入数据库（ordermaster orderdetail）
            detail.setOrderId(new Random(System.currentTimeMillis()).nextInt(1000));
            detail.setId(anInt);
            BeanUtils.copyProperties(productInfo, detail);
            orderDetailRepository.saveOrUpddate(detail);
            
        //121
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrder_id(anInt);
        orderMaster.setOrderAmount(amount);
        BeanUtils.copyProperties(orderDto, orderMaster);
        masterRepository.saveOrUpdate(orderMaster);
        
        // 扣库存---
        List<CartDto> collect = orderDto.getOrderDetail.stream().map((detail) -> new CartDto(detail.getProductid(), detail.getProductQuantity()))
                .collect(Collectors.toList());//手机所有的商品数量和id 在别的业务中进行库存的扣
        productService.decStore(collect);
        
        return orderDto;
    }
    
    //    查询
    @Override
    public OrderDto getByOne(Integer orderId) {
//        逻辑判断
        OrderMaster orderMaster = masterRepository.getById(orderId);
        
        if(null==orderMaster){
            throw new RuntimeException("订单不存在");
        }
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(detailList)){
            throw new RuntimeException("订单明细不存在");
        }
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setGetOrderDetail(detailList);
        return orderDto;
    }
    
    @Override
    public List<OrderDto> findList() {
        return null;
    }
    
    //    取消订单
    @Override
    public OrderDto cancleOrder(OrderDto orderDto) {
//        判断订单状态
       
        Integer orderStatus = orderDto.getOrderStatus();
        if(orderStatus== OrderStatus.NEW.getCode()){
            log.error("【取消订单】:订单状态不正确{}, 订单状态"+orderDto.getOrderStatus());
            throw new RuntimeException("修改订单状态不正确");
        }
    
//        修改订单状态
        orderDto.setOrderStatus(OrderStatus.CANCLE.getCode());
        masterRepository.saveOrUpdate(orderDto);
//       返回库存
        List<CartDto> collect = orderDto.getGetOrderDetail().stream().map((orderDetail -> new CartDto(orderDetail.getProductid(), orderDetail.getProductQuantity())))
                .collect(Collectors.toList());
        productService.addStore(collect);
//        如果已经支付需要退款
        if(orderDto.getOrderStatus().equals(OrderStatus.FINISHED.getCode())){
//            todo
        }
        return orderDto;
    }
    
    //    完成订单
    @Override
    public OrderDto finish(OrderDto orderDto) {
//        判断订单状态 新建状态才可以
//  修改订单状态
        return null;
    }
    
    //    支付订单
    @Override
    public OrderDto pay(OrderDto orderDto) {
//        判断订单状态
//        判断支付状态
//        修改支付支付状态
        return null;
    }
    
    
}
