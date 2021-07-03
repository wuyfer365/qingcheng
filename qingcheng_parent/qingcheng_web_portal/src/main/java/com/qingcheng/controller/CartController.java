package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.user.Address;
import com.qingcheng.pojo.user.User;
import com.qingcheng.service.order.CartService;
import com.qingcheng.service.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Reference
    private CartService cartService;

    @GetMapping("/findCartList")
    public List<Map<String, Object>> findCartList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Map<String, Object>> cartList = cartService.findCartList(username);
        return cartList;
    }

    @GetMapping("/addItem")
    public Result addItem(String skuId,Integer num) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.addItem(username,skuId,num);
        return new Result();
    }
    @GetMapping("/buy")
    public void buy(HttpServletResponse response,String skuId,Integer num) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.addItem(username,skuId,num);
        response.sendRedirect("/cart.html");

    }

    @GetMapping("/updateChecked")
    public Result updateChecked(String skuId, boolean checked) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.updateChecked(name, skuId, checked);
        return new Result();
    }

    @GetMapping("/deleteCheckedCart")
    public Result deleteCheckedCart() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.deleteCheckedCart(name);
        return new Result();
    }

    @GetMapping("/preferential")
    public Map preferential() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int preferential = cartService.preferential(name);
        Map map = new HashMap();
        map.put("preferential",preferential);
        return map;
    }

    /**
     * 获取最新价格的购物车
     * @return
     */
    @GetMapping("/findNewOrderItemList")
    public List<Map<String, Object>> findNewOrderItemList() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Map<String, Object>> newOrderItemList = cartService.findNewOrderItemList(name);
        return newOrderItemList;
    }

    @Reference
    AddressService addressService;
    @GetMapping("/findAddressList")
    public List<Address> findAddressList() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Address> addressList = addressService.findByUsername(name);
        return addressList;
    }

}
