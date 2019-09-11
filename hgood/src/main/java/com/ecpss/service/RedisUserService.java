package com.ecpss.service;

import com.ecpss.domain.User;

/**
 * Created by xc on 2019/9/4.
 */
public interface RedisUserService {

     User getByid(Long id);
     User getHot(String name);
     User getData(Long id);
}

