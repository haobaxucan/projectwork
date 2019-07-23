package com.ecpss.service;

import com.ecpss.spring.domain.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xc on 2019/6/21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderServiceTest {
    @Autowired
    private VideoOrderService service;
  
    @Test
    public void findAll() throws Exception {
        List<VideoOrder> list = service.findAll();
        assertNotNull(list);
        
//        System.out.println(list.size());
    }
    
    @Test
    public void findById() throws Exception {
        VideoOrder videoOrder = service.findById(1);
        assertNotNull(videoOrder);
    }
    
    @Test
    public void delete() throws Exception {
       service.delete(4);
    }
    
    @Test
    public void saveOrUpdate() throws Exception {
        VideoOrder videoOrder = service.findById(2);
        videoOrder.setDel(1);
//        service.saveOrUpdate(videoOrder);
    
    }
    
}