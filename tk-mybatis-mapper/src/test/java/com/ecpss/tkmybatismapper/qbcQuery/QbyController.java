package com.ecpss.tkmybatismapper.qbcQuery;

import com.ecpss.tkmybatismapper.bean.Book;
import com.ecpss.tkmybatismapper.bean.User;
import com.ecpss.tkmybatismapper.bean.UserBook;
import com.ecpss.tkmybatismapper.mapper.BookMapper;
import com.ecpss.tkmybatismapper.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    @Autowired
    private BookMapper bookMapper;

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

    @Test
    public void testBook(){//查询多表
//        SELECT  u.name,u.addx,b.name,b.price FROM userd1  u INNER JOIN book b ON u.id=b.uid WHERE b.uid=1;
//        根据用户id 查询所有的的书籍名称价格
        List<UserBook> userBooks=new ArrayList<>();
        Integer id=1;
        Example userExample=new Example(User.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("id",id);
        List<User> users = userMapper.selectByExample(userExample);
        users.forEach((user -> {
            Example bookExample=new Example(Book.class);
            Example.Criteria bookCriteria = bookExample.createCriteria();
            bookCriteria.andEqualTo("uid",id);
            List<Book> books = bookMapper.selectByExample(bookExample);
            books.forEach((book -> {
                UserBook userBook=new UserBook();
                userBook.setUserName(user.getName());
                userBook.setBookName(book.getName());
                userBooks.add(userBook);

            }));

        }));
        userBooks.forEach((userBook -> {
//            System.out.println(userBook.getUserName());
            System.out.println(userBook.getBookName());

        }));




    }


}
