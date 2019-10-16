package com.ecpss.tkmybatismapper.controller;

import com.ecpss.tkmybatismapper.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.00
 * @date 2019/9/30
 */
@Controller
@Api(value = "用户模块",description = "用户模块信息")
public class UserController {

    public  static List<User> users=new ArrayList<>();
    static {
//        users.add(new User().setName("aa"));
//        users.add(new User().setName("bb"));
    }

    @GetMapping("/users")
    @ResponseBody
    @ApiOperation(value = "获取用户列表",notes = "获取所有用户列表")
    public Object Users(){
        Map<String,Object> map=new HashMap<>();
        map.put("users",users);
        return map;
    }
    @GetMapping("/user")
    @ResponseBody
    @ApiOperation(value = "获取单个用户",notes = "根据指定的id获得用户")
    public Object User(){
        Map<String,Object> map=new HashMap<>();
        map.put("users",users);
        return map;
    }


}
