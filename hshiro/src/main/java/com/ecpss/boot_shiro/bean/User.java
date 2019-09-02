package com.ecpss.boot_shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xc on 2019/8/4.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
   
    private Integer id;
    private String username;
    private String password;
    
    private String pers;
    
    
}
