package org.csu.tank.controller;


import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.csu.tank.service.CartService;
import org.csu.tank.service.CatalogService;
import org.csu.tank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    private CatalogService  catalogService;
    @GetMapping("/cart/{username}")
    public Cart getCartByUsername(@PathVariable("username")String username){
        return cartService.getCartByUsername(username);
    }

    @PostMapping("/cart/add")
    public boolean insertCartItem(@RequestBody CartItem cartItem){
        try {
            Item item = catalogService.getItem(cartItem.getItemId());
            cartService.insertCartItem(item,cartItem.getCartId(),cartItem.getCount());
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @DeleteMapping("/cart/delete")
    public boolean deleteCartItem(@RequestBody CartItem cartItem){
        try {
            if(cartItem.getCartId() == 0)
                return false;

            cartService.deleteCartItem(cartItem);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    //true false 返回错误
    @PutMapping("/cart/recount")
    public boolean recount(@RequestBody CartItem cartItem){
        try {
            if (cartItem.getCount()<=0)
                return false;
            cartService.increment(cartItem);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    //true false可以
    @DeleteMapping("/cart/checkOut")
    public boolean checkOut(@RequestBody CartItem []cartItem,@RequestParam String username,@RequestParam int addressId){
        try {
            int []itemId = new int[cartItem.length];
            int []count = new int [cartItem.length];
            for (int i = 0;i<cartItem.length;i++){
                cartService.deleteCartItem(cartItem[i]);
                itemId[i] = cartItem[i].getItemId();
                count[i] = cartItem[i].getCount();
            }
            orderService.insertOrder(username,1,itemId,count,addressId);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
