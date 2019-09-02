package com.ecpss;

import com.ecpss.boot_shiro.bean.User;
import com.ecpss.boot_shiro.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HshiroApplicationTests {
@Autowired
private UserMapper userMapper;
	@Autowired
	DataSource dataSource;
	@Test
	public void contextLoads() throws Exception{
		User user = userMapper.getUser(1);
		System.out.println(user);
//		System.out.println(user.getId()+"-");
		User xc = userMapper.getUserByName("xc");
		System.out.println(xc);
		
	}

}
