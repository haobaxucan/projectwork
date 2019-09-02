package com.ecpss.service;

import com.ecpss.domain.User;

/**
 * Created by xc on 2019/8/26.
 */
public interface Userservice {
    
    User getUser(String userName);
}
