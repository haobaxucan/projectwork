package com.ecpss.dao;

import com.ecpss.spring.domain.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by xc on 2019/6/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository repository;
    @Test
    public void saveOrUpdate() throws Exception {
    
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrder_id(1234);
        orderMaster.setBuyerName("xaoxcao");
        orderMaster.setBuyerPhone("213d4543653");
        orderMaster.setPayStatus(0);
        orderMaster.setCreateTime(new Date());
        orderMaster.setBuyerOpenid("12331");
        repository.saveOrUpdate(orderMaster);
    
    }
    
}