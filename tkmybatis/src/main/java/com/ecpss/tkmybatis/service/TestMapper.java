package com.ecpss.tkmybatis.service;

import com.ecpss.tkmybatis.com.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @version 1.00
 * @date 2019/9/26
 */
@Mapper
@Repository
public interface TestMapper extends tk.mybatis.mapper.common.Mapper<TestUser> {
    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    @Override
    int deleteByPrimaryKey(Object key);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param record
     * @return
     */
    @Override
    int delete(TestUser record);

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Override
    int insert(TestUser record);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Override
    int insertSelective(TestUser record);

    /**
     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    @Override
    boolean existsWithPrimaryKey(Object key);

    /**
     * 查询全部结果
     *
     * @return
     */
    @Override
    List<TestUser> selectAll();

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    @Override
    TestUser selectByPrimaryKey(Object key);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    @Override
    int selectCount(TestUser record);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    @Override
    List<TestUser> select(TestUser record);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */

    TestUser selectOne(TestUser record);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    @Override
    int updateByPrimaryKey(TestUser record);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    @Override
    int updateByPrimaryKeySelective(TestUser record);

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    @Override
    int deleteByExample(Object example);

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @Override
    List<TestUser> selectByExample(Object example);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    @Override
    int selectCountByExample(Object example);

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @Override
    TestUser selectOneByExample(Object example);

    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    @Override
    int updateByExample(TestUser record, Object example);

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    @Override
    int updateByExampleSelective(TestUser record, Object example);

    /**
     * 根据example条件和RowBounds进行分页查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    @Override
    List<TestUser> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    @Override
    List<TestUser> selectByRowBounds(TestUser record, RowBounds rowBounds);
}
