package com.ecpss.tkmybatismapper.base;

import com.ecpss.tkmybatismapper.Base.test.common.utils.DateTimeUtils;
import com.ecpss.tkmybatismapper.Base.test.dao.CityDao;
import com.ecpss.tkmybatismapper.Base.test.entity.CityEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @version 1.00
 * @date 2019/9/27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseCityTest {

    @Autowired
    private CityDao cityDao;

    @Test
    public void test1() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("湖南22");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        cityEntity.set
        String dateStr = sdf.format(new Date());
        cityEntity.setUtime(new Date());
        cityDao.insertSelective(cityEntity);

    }

    @Test
    public void testTimeStamp() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setAddx("1dddddd");
//        cityEntity.setPlantime();
        cityEntity.setPlantime(new Timestamp(System.currentTimeMillis()));
        cityDao.insertSelective(cityEntity);
    }

    @Test
    public void testQueryByTime() {
        Example example=new Example(CityEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan("utime",new Date());
        List<CityEntity> cityEntities = cityDao.selectByExample(example);
        cityEntities.forEach((c)->c.getName());
        //Caused by: java.sql.SQLException: Zero date value prohibited
//        1解决办法 &zeroDateTimeBehavior=CONVERT_TO_NULL
    }

    @Test
    public void testQueryByTimeStamp() {
        Example example=new Example(CityEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan("plantime",new Date());
        List<CityEntity> cityEntities = cityDao.selectByExample(example);
        cityEntities.forEach((c)-> System.out.println(c.getAddx()));
    }


}
