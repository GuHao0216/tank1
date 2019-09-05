package org.csu.tank.controller;


import com.alibaba.fastjson.JSONObject;
import org.csu.tank.base.Response;
import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.csu.tank.service.CartService;
import org.csu.tank.service.CatalogService;
import org.csu.tank.service.OrderService;
import org.csu.tank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.csu.tank.base.Response.fail;
import static org.csu.tank.base.Response.success;

@RestController
@CrossOrigin(allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CatalogService  catalogService;

    @GetMapping("/cart")
    public Response getCartByUsername(HttpServletRequest request){
        String tokenName = JwtUtil.getUsernameByToken(request.getHeader("token"));
        JSONObject object = new JSONObject();
        object.put("cart", cartService.getCartByUsername(tokenName));
        return success(object);
    }

    @PostMapping("/cart/add")
    public Response insertCartItem(@RequestBody CartItem cartItem){
        try {
            Item item = catalogService.getItem(cartItem.getItemId());
            cartService.insertCartItem(item,cartItem.getCartId(),cartItem.getCount());
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return fail("添加商品进购物车失败！");
        }

    }

    @DeleteMapping("/cart/delete")
    public Response deleteCartItem(@RequestBody CartItem cartItem){
        try {
            if(cartItem.getCartId() == 0) {
                JSONObject object = new JSONObject();
                object.put("flag", false);
                return success(object);
            }

            cartService.deleteCartItem(cartItem);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return fail("删除购物车商品失败！");
        }
    }


    @PutMapping("/cart/recount")
    public Response recount(@RequestBody CartItem cartItem){
        try {
            if (cartItem.getCount()<=0) {
                JSONObject object = new JSONObject();
                object.put("flag", false);
                return success(object);
            }
            cartService.increment(cartItem);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return fail("修改购物车商品数目失败！");
        }
    }

    @DeleteMapping("/cart/checkOut")
    public Response checkOut(@RequestBody CartItem []cartItem,@RequestParam String username,@RequestParam int addressId){
        try {
            int []itemId = new int[cartItem.length];
            int []count = new int [cartItem.length];
            for (int i = 0;i<cartItem.length;i++){
                cartService.deleteCartItem(cartItem[i]);
                itemId[i] = cartItem[i].getItemId();
                count[i] = cartItem[i].getCount();
            }
            orderService.insertOrder(username,1,itemId,count,addressId);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return fail("结算失败！");
        }
    }


}
