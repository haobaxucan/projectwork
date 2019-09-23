package com.ecpss.cart;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class CartController {
    
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    /**
     * cookie 中 没有值，添加
     * cookie中 有值 更新
     * @param id
     * @param quantity
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/cartAdd")
    public String cart(int id, int quantity,String pwd, HttpServletResponse response, HttpServletRequest request){
        request.getSession().setAttribute("pwd","123");
        log.info("id={},quantity={}",id,quantity);
        //  判断用户是否登录，没有就是cookie,有就是db
//       通过参数查询商品
//        封装购物车
        if (pwd.equals("123")) {
            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(quantity);
        
            Cart cart1 = new Cart();
            cart.setId(id);
            cart.setQuantity(quantity);
            List<Cart> carts=new ArrayList<>();
            carts.add(cart1);
            String cookieValue = CookieUtil.getCookieValue(request, "cart", true);
            if (StringUtils.isEmpty(cookieValue)){
               carts.add(cart);//购物车加入到cookie中，之前没有有购物车
    
            }else {
                List<Cart> cartList = JSONObject.parseArray(cookieValue, Cart.class);
                if (cart_contain_good(cartList,id)){//id相等更新购物车儿的数量，之前没有有购物车
    //                更新数量
                }else{
                    carts.add(cart);
                }
        
            }
            CookieUtil.setCookie(request,response,"cart",JSONObject.toJSONString(carts),60*60,true);
        }else {
//            用户登录
//           查询数据库
//            判断数据库 有没有该商品，如果没有，添加到数据库
//          如果没有，更新数据库
        
        }
//       最后 同步缓存
        stringRedisTemplate.opsForHash().put("a","dd","ee");
        return "redirect:/cartList";
    
    }
    
    private boolean cart_contain_good(List<Cart> cartList, int id) {
       for(Cart cart :cartList){
           if(cart.getId()==id){
               return false;
           }
       }
        return true;
    }
    
    @RequestMapping("/cartList")
    public String list(HttpServletRequest request, Model model){
        //没登录 从cookie中取值
        log.info("重定向");
        List<Cart> carts=new ArrayList<>();
        String cart = CookieUtil.getCookieValue(request, "cart", true);
        String pwd=(String) request.getSession().getAttribute("pwd");
        if (pwd.equals("123")) {
           carts = JSONObject.parseArray(cart, Cart.class);
        }
        model.addAttribute("carts",carts);
        //登录 从db中查询 需要if 判断
        return "cart";
    }
    
    @RequestMapping("/inner")
    public String inner(HttpServletRequest request, Model model){
        log.info("开始内嵌页面");
        
        
        return "inner";
    }
    
}
