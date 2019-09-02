package com.ecpss.dao;

import com.ecpss.domain.User;

/**
 * Created by xc on 2019/8/26.
 */
public interface UserDao {

    User getByName(String name);

}
