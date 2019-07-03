package com.ecpss.service;

import com.ecpss.domain.User;
import com.ecpss.exception.Response;

/**
 * Created by xc on 2019/6/25.
 */
public interface UserService {
    /**
     * <h2>创建用户</h2>
     * @param user {@link User}
     * @return {@link Response}
     * */
    Response createUser(User user) throws Exception;
}
