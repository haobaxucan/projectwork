package com.ecpss.controller;

import com.ecpss.controller.dto.OrderDto;
import com.ecpss.controller.dto.test.Person;
import com.ecpss.controller.form.DtoUtils;
import com.ecpss.controller.form.OrderForm;
import com.ecpss.service.OrderService;
import com.ecpss.utils.HttpUtils;
import com.ecpss.utils.JpaPageHelper;
import com.ecpss.utils.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/6/27.
 */
@Slf4j
@Controller
public class TestController {
    @Autowired
    private OrderService orderService;
    
    /**
     * 备注：这里一个@Valid的参数后必须紧挨着一个BindingResult 参数，
     * 否则spring会在校验不通过时直接抛出异常
     *
     * @param
     * @param result 使用@Valid+BindingResult进行controller参数校验
     */
    @RequestMapping("/ct")//创建订单
    @ResponseBody
    public void Ct(@RequestBody @Valid OrderForm form, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
        }


//         意思是接收orderform,但是我们创建订单得时候需要orderDto
        OrderDto orderDto = DtoUtils.convertOrderDto(form);
        orderService.createOrder(orderDto);
    }
    
    /**
     * 订单详情
     */
    @RequestMapping("/find")//取消订单
    public void findOne(@RequestParam("order_id") Integer id, @RequestParam("openid") String openId) {
//        todo
        OrderDto orderDto = orderService.getByOne(id);
    }
    
    
    @RequestMapping("/cancle")//取消订单
    public void cancle(@RequestParam("order_id") Integer id, @RequestParam("openid") String openId) {
        OrderDto orderDto = orderService.getByOne(id);
        orderService.cancleOrder(orderDto);
    }
    
    @RequestMapping("/t")
    public void t() {
        
        System.out.println("ccc2");
    }
    
    @ResponseBody
    @RequestMapping(value = "/yz", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String getWxUserInfo(HttpServletRequest request,
                                @RequestParam(required = false) String echostr,
                                @RequestParam(required = false) String signature,
                                @RequestParam(required = false) String timestamp,
                                @RequestParam(required = false) String nonce) {
        try {
            //只需要把微信请求的 echostr, 返回给微信就可以了
            System.out.println("测试来过===================" + echostr);
            System.out.println("测试来过===================" + signature);
            System.out.println("测试来过===================" + timestamp);
            System.out.println("测试来过======" + nonce);
            return echostr;
        } catch (Exception e) {
            System.out.println("测试微信公众号的接口配置信息发生异常：");
            return "错误！！！";
        }
        
    }
    
    @RequestMapping("/wx")
    public void buy(@RequestParam("code") String code) {
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx30de7e8ac3923ce2&secret=e98584dc55427d15b60c4937084e530d&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> map = HttpUtils.doGet(url);
        System.out.println(map);
        
        
    }
    
    /**
     * https://github.com/chanjarster/weixin-java-tools/wiki
     */
    @RequestMapping("wk")
    public String wk() {
    
    return "collect";
    }
    
    @RequestMapping("/a")
    public String string(Model model,@RequestParam(value = "page",defaultValue = "1")Integer cpage,@RequestParam(value = "size",defaultValue = "2")Integer size) {
        log.info("cpage{},size={}",cpage,size);
        List<Person> list=new LinkedList();
        Person p1=new Person("xc","城市",21);
        Person p2=new Person("de","城市",21);
        Person p3=new Person("fd","城市21",21);
        Person p4=new Person("bf","21城市",21);
        Person p5=new Person("vhfdf","患难",21);
        Person p6=new Person("vx","廉租",21);
        Person p7=new Person("vjgz","调教",21);
        Person p8=new Person("vduf","城市从点",21);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        Pageable pageable=new QPageRequest(cpage,size);
        PageImpl<Person> page=new PageImpl<Person>(list,pageable,list.size());
        model.addAttribute("lis",list);
/**
 *
 * page 是一个集合的对象，每一个p都是如此
 Person(name=xc, addr1=城市, age=21)
 Person(name=de, addr1=城市, age=21)
 Person(name=fd, addr1=城市21, age=21)
 */
        model.addAttribute("page",page);
        
        return "shouye";
    }
    @RequestMapping("/xucan")
    public String xc(){
        
        return "xucan";
    }
    
    @RequestMapping("/shouji")
    public String shouji(){
        
        return "test";
    }
    @RequestMapping("/toCollect")
    public String toCollect(@RequestParam("name")String name,@RequestParam("pwd")String pwd){
        log.info("name={} , pwd={}",name,pwd);
        return "test";
    }
    
    @RequestMapping("/index")
    public String index(){
        
        return "index";
    }
}
