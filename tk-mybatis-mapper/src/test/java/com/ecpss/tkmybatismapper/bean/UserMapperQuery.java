package com.ecpss.tkmybatismapper.bean;

import com.ecpss.tkmybatismapper.mapper.UserMapper;
import com.ecpss.tkmybatismapper.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperQuery {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheService cacheService;

    /**
     * 查询一个
     */
    @Test
    public void test1(){ //SELECT id,name,addx FROM userd1 WHERE name = ? AND addx = ?
        User user=new User();
        user.setName("1");
        user.setAddx("cx");
        User selectOne = cacheService.selectOne(user);
        System.out.println(selectOne.getId());
        User selectOne1 = cacheService.selectOne(user);
        System.out.println(selectOne1.getId());
    }
    @Test
    public void test2(){//查询 列表 SELECT id,name,addx FROM userd1 WHERE name = ? AND addx = ?
        User user=new User();
        user.setName("dec");        user.setAddx("re");
        List<User> select = userMapper.select(user);
        select.forEach(System.out::print);
    }
    @Test
    public void test3(){//SELECT id,name,addx FROM userd1   查询所有
        List<User> users = userMapper.selectAll();
        System.out.println(users.size());
    }

    @Test
    public void test4(){//查询行数
        int i = userMapper.selectCount(null);//SELECT COUNT(id) FROM userd1
        System.out.println(i);
        User user=new User();
        user.setName("xc").setAddx("xx");
        int i1 = userMapper.selectCount(user);//SELECT COUNT(id) FROM userd1 WHERE name = ? AND addx = ?
        System.out.println(i1);
    }
    @Test
    public void test5() {//通过主键查询
        User user = userMapper.selectByPrimaryKey(12);//SELECT id,name,addx FROM userd1 WHERE id = ?
        System.out.println(user);
    }
    @Test
    public void test6() {//判断主键是否存在
//      SELECT CASE WHEN COUNT(id) > 0 THEN 1 ELSE 0 END AS result FROM userd1 WHERE id = ?
        boolean bool = userMapper.existsWithPrimaryKey(12);
        System.out.println(bool);
    }
}