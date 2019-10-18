package com.ecpss.tkmybatismapper.controller;

import com.ecpss.tkmybatismapper.Base.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.00
 * @date 2019/10/12
 */
@Controller
public class IdsController {
    @GetMapping("/index")
    public String Users(){
        return "index";
    }
    @RequestMapping("/read")
    @ResponseBody
    public String batchReadInMail(Integer cid, Integer ids[]) { //这里传入的参数必须为数值
        System.out.println(cid);
        if (ids!=null&&ids.length>0) {
            Arrays.stream(ids).forEach(System.out::println);
            System.out.println("请求这里");
        }
        return "";
    }

}
