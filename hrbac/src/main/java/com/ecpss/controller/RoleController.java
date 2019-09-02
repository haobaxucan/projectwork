package com.ecpss.controller;

import com.ecpss.User.Role;
import com.ecpss.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/29.
 */
@Slf4j
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/index")
    public String index1(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false,
            defaultValue = "3") Integer pageSize, Model model) {
//    用户分页显示
        Map<String, Object> map = new HashMap<>();
        map.put("start", (pageNo - 1) * pageSize);
        map.put("size", pageSize);
        
        List<Role> roles = roleService.pageQuery(map);
        model.addAttribute("roles", roles);
//        当前页
        model.addAttribute("pageNo", pageNo);
//        最大页
        int count = roleService.queryAllCount();
        int totolNo = 0;
        if (count % pageSize == 0) {
            totolNo = count / pageSize;
        } else {
            totolNo = count / pageSize + 1;
        }
        log.info("总页数{},当前页{}", totolNo, pageNo);
        model.addAttribute("totalNo", totolNo);
        return "/role/role";
    }


}
