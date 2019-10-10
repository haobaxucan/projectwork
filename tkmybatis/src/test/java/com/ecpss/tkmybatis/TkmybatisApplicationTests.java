package com.ecpss.tkmybatis;

import com.ecpss.tkmybatis.com.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.ecpss.tkmybatis.service")
public class TkmybatisApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(TkmybatisApplicationTests.class);
    @Autowired
    DataSource dataSource;
    @Test
    public void contextLoads() throws Exception{
        System.out.println(dataSource.getConnection());
    }
    @Autowired
    private UserMapper userMapper;
    @Test
    public void data() throws Exception{
        User user = userMapper.getById(12);
//        System.out.println(user.getName()+"--"+user.getAdda());
    }

    @Test
    public void data1() throws Exception{
        User user = userMapper.getByIdAndAddr(12, "re");
        logger.info("chaoshende jiehzou -------------");
        System.out.println(user.getName()+"--"+user.getAdda());
    }

    @Test
    public void dataPo() throws Exception{
        User user=new User();
        user.setId(12);
        user.setName("dec");
        userMapper.getPo(user);
        System.out.println(user.getName()+"--"+user.getId());
    }
    @Test
    public void dataMap() throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("username","dec");
        map.put("address","re");
        User user = userMapper.getMap(map);
        System.out.println(user.getName()+"--"+user.getId());
    }

    @Test
    public void page() throws Exception{
        PageHelper.startPage(1,3);
        List<User> getlist = userMapper.getlist();
        PageInfo<User> userPageInfo=new PageInfo<>(getlist);
        System.out.println("总页数="+userPageInfo.getPages());


    }


}
