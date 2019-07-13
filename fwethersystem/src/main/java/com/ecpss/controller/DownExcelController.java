package com.ecpss.controller;

import com.ecpss.enumCode.ErrorCode;
import com.ecpss.enumCode.ResponseCode;
import com.ecpss.test.User;
import com.ecpss.util.excelutil.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/7/10.
 */
@Slf4j
@Controller
public class DownExcelController {
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request) throws Exception{
        
        log.info("开始文件上传{}",file.getOriginalFilename());
        String keyValue="姓名:name,密码:password,地址:address,年龄:age";//全部参数匹配
        List<User> users = ExcelUtil.readXls(file.getBytes(), ExcelUtil.getMap(keyValue), "com.ecpss.test.User");
        users.forEach(System.out::print);
        return result(true,"成功");
    
    }
    
    @RequestMapping(value = "/downExcel")
    @ResponseBody
    public ResponseCode down(HttpServletResponse response) {
        List<User> users=new ArrayList<>();
        User user=new User("xx",11,"ss","dd");
        users.add(user);
        String keyValue="姓名:name,密码:password,地址:address,年龄:age";//全部参数匹配
        try {
            ExcelUtil.exportExcelOutputStream(response,keyValue,users,"com.ecpss.test.User","");
        } catch (Exception e) {
            log.error("操作异常{}",e);
          return new ResponseCode(ErrorCode.Fail.getText());
        }
        return new ResponseCode(ErrorCode.SUCESS.getText());
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public String ajaxTest(@RequestParam("name")String name) {
        log.info("请求到了{}",name);
        return "ajax";
    }
    
    @PostMapping(value = "/testUser")
    @ResponseBody
    public List<User> testImport(MultipartFile file) throws Exception{
    
        String keyValue="姓名:name,密码:password,地址:address,年龄:age";//全部参数匹配
        List<User> list = ExcelUtil.readXls(file.getBytes(), ExcelUtil.getMap(keyValue), "com.ecpss.test.User");
        return list;
    }
    
    private Map<String, Object> result(boolean isSuccess, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("msg", msg);
        return result;
    }

}
