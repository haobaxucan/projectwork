package com.ecpss.controller;

import com.ecpss.User.Role;
import com.ecpss.User.User;
import com.ecpss.service.RoleService;
import com.ecpss.service.UserService;
import com.ecpss.vo.Page;
import com.ecpss.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/28.
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/index1")
    public String index() {
        
        return "user/index1";
    }
    
    @RequestMapping("/pageQuery")
    @ResponseBody
    public Object pageQuery(Integer pageNo, Integer pageSize) {
        ResultVo vo = new ResultVo();
//        异步刷新数据 不需要model 传入参数
//        自己创建一个分页对象page 来封装数据
        
        try {
            
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageNo - 1) * pageSize);
            map.put("size", pageSize);
            
            List<User> users = userService.pageQuery(map);
            
            int count = userService.queryAllCount();
            int tatolNo = 0;
            if (count % pageSize == 0) {
                tatolNo = count / pageSize;
            } else {
                tatolNo = count / pageSize + 1;
            }
//            准备分页对象
            Page<User> page = new Page<>();
            page.setUsers(users);
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotalSize(tatolNo);
            vo.setSucc(true);
            vo.setDate(page);
        } catch (Exception e) {
            vo.setSucc(false);
        }
        return vo;
    }
    
    /**
     * 查询--包含了模糊查询
     *
     * @param pageNo
     * @param pageSize
     * @param model
     * @param queryText
     * @return
     */
    @RequestMapping("/index")
    public String index1(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false,
            defaultValue = "3") Integer pageSize, Model model, @RequestParam(value = "queryText", required = false) String queryText) {
//    用户分页显示
        Map<String, Object> map = new HashMap<>();
        map.put("start", (pageNo - 1) * pageSize);
        map.put("size", pageSize);
        map.put("queryText", queryText);
        
        List<User> users = userService.pageQuery(map);
        model.addAttribute("users", users);
//        当前页
        model.addAttribute("pageNo", pageNo);
//        最大页
        int count = userService.queryAllCount();
        int totolNo = 0;
        if (count % pageSize == 0) {
            totolNo = count / pageSize;
        } else {
            totolNo = count / pageSize + 1;
        }
        log.info("总页数{},当前页{},queryText={}", totolNo, pageNo, queryText);
        model.addAttribute("totalNo", totolNo);
        
        return "user/index";
    }
    
    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }
    
    
    @RequestMapping("/insert")
    @ResponseBody  //注意这里不用跳转页面------------
    public Object insert(User user) {
        ResultVo vo = new ResultVo();
        
        try {
            userService.insert(user);
            vo.setSucc(true);
            log.info("插入数据成功");
        } catch (Exception e) {
            vo.setSucc(false);
            log.error("插入数据失败");
        }
        return vo;
    }
    
    @RequestMapping("/edit")
    public String userEdit(@RequestParam("id") Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        return "/user/edit";
    }
    
    @RequestMapping("/doEdit")
    @ResponseBody
    public Object doEdit(User user, Model model) {
        ResultVo vo = new ResultVo();
        log.info("用户id={}", user.getId());
        try {
            userService.updateUser(user);
            vo.setSucc(true);
            log.info("修改数据成功");
        } catch (Exception e) {
            log.info("修改数据失败");
        }
        return vo;
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {//不加@requestParam 注解必须完全匹配一致
        ResultVo vo = new ResultVo();
        log.info("用户id={}", id);
        try {
            userService.delete(id);
            vo.setSucc(true);
            log.info("删除数据成功");
        } catch (Exception e) {
            log.info("删除数据失败");
        }
        return vo;
    }
    
    /**
     * 批量删除
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public Object delUsers(Integer[] userid) {
        ResultVo vo = new ResultVo();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user_ids", userid);
            vo.setSucc(true);
            userService.delUsers(map);
            
        } catch (Exception e) {
            log.info("删除数据失败");
            vo.setSucc(false);
        }
        return vo;
    }
    
    /**
     * 角色操作
     */
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/assign")
    public String assign(Integer id, Model model) {
        log.info("用户id={}", id);
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.queryAll();
        
        List<Role> assign = new ArrayList<>();
        List<Role> unAssign = new ArrayList<>();
        List<Integer> roleids = userService.queryRoleById(id);
        log.info("roleids 的角色id数目{}",roleids.size());
        for(Integer roleid:roleids){
            Role assignRole= roleService.queryById(roleid);
             log.info("分配的角色名称",assignRole.getName());
            assign.add(assignRole);
        }
        for(Role role:roles){
            if(!assign.contains(role)){
                unAssign.add(role);
            }
        }
       log.info("没有分配的集合数量{}",unAssign.size());
        model.addAttribute("userid", id);
        model.addAttribute("assign", assign);
        model.addAttribute("unAssign", unAssign);
        return "/user/assignRole";
        
    }
    
    //    分配角色
    @RequestMapping("/doAssign")
    @ResponseBody
    public Object doAssign(Integer userid, Integer[] unRoleIds) {
        ResultVo vo = new ResultVo();
        try {
            Map<String, Object> map = new HashMap<>();
            log.info("接收到长度{},用户id={}", unRoleIds.length, userid);
            map.put("userid", userid);
            map.put("unRoleIds", unRoleIds);
            
            userService.insertUserRoles(map);
            vo.setSucc(true);
        } catch (Exception e) {
            log.info("分配角色数据失败");
            vo.setSucc(false);
        }
        return vo;
    }
    
    //    分配角色
    @RequestMapping("/unDoAssign")
    @ResponseBody
    public Object unDoAssign(Integer userid, Integer[] roleIds) {
        ResultVo vo = new ResultVo();
        try {
            Map<String, Object> map = new HashMap<>();
            
            
            vo.setSucc(true);
        } catch (Exception e) {
            
            vo.setSucc(false);
        }
        return vo;
    }
    
    
}

