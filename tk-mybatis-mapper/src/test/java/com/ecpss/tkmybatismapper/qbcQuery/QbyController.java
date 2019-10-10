package com.ecpss.tkmybatismapper.qbcQuery;

import com.ecpss.tkmybatismapper.bean.User;
import com.ecpss.tkmybatismapper.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QbyController {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQbc(){
        Example example=new Example(User.class);
        example.setDistinct(true);//去重
        example.orderBy("name").asc();//排序
        example.selectProperties("name","addx");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name","xc");
//       criteria.andGreaterThan("","");
//        SELECT id,name,addx FROM userd1 WHERE ( name = ? )
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users.size());

    }

    @Test
    public void testQbcUpdate(){
        //在criteria里面设置的是条件中设置
        // 需要修改的参数在实体类entity设置
        Example condition=new Example(User.class);
        Example.Criteria criteria = condition.createCriteria();
        User user=new User();
        user.setId(8);
        user.setAddx("jjjjjjjjjjjjjj");
        criteria.andEqualTo("addx","hgh");
        userMapper.updateByExampleSelective(user,condition);


    }

}
