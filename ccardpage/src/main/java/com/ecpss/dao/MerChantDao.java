package com.ecpss.dao;

import com.ecpss.spring.domain.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by xc on 2019/6/25.
 */
public interface MerChantDao extends JpaRepository<Merchants,Long>{
    /**
     * <h2>通过 id 获取商户对象</h2>
     * @param id 商户 id
     * @return {@link Merchants}
     * */
    Merchants findById(Integer id);
    
    /**
     * <h2>根据商户名称获取商户对象</h2>
     * @param name 商户名称
     * @return {@link Merchants}
     * */
    Merchants findByName(String name);
    
    /**
     * <h2>根据商户 ids 获取商户对象</h2>
     * @param ids 商户 ids
     * @return {@link Merchants}
     * */
    List<Merchants> findByIdIn(List<Integer> ids);

}
