package com.ecpss.tkmybatismapper.bean;

import com.ecpss.tkmybatismapper.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Insert_Update_Delete {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void add1() { //增加  INSERT INTO userd1 ( id,name,addx ) VALUES( ?,?,? )
        User user = new User();
        user.setName("zhutian");
        userMapper.insert(user);
    }
    @Test//好处xxxSelective 除了主键外的所有字段为空则，不加入到sql 语句中
    public void add2() {//INSERT INTO userd1 ( id,name ) VALUES( ?,? )
        User user = new User();
        user.setName("chaoshen1");
        userMapper.insertSelective(user);
    }
    @Test
    public void update1() {//UPDATE userd1 SET id = id,name = ?,addx = ? WHERE id = ? (作用不大)
        User user = new User();
        user.setId(1);
        user.setName("xxxx");
        userMapper.updateByPrimaryKey(user);

    }

    @Test
    public void update2() {//UPDATE userd1 SET id = id,name = ? WHERE id = ?
        User user = new User();
        user.setId(2);
        user.setName("111111111");
        userMapper.updateByPrimaryKeySelective(user);
    }
    @Test
    public void delete1() {//DELETE FROM userd1 WHERE id = ?
        User user = new User();
        user.setId(2);
        userMapper.delete(null); //注意这里如果传入的之为null,则是删除所有的表 DELETE FROM userd1
    }
    @Test
    public void delete2() {//DELETE FROM userd1 WHERE id = ?
        userMapper.deleteByPrimaryKey(3);
    }

}
