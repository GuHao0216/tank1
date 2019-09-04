package org.csu.tank.controller;


import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.csu.tank.service.CartService;
import org.csu.tank.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
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
            System.out.println(cartItem.getCartId());
            cartService.deleteCartItem(cartItem);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @PutMapping("/cart/recount")

    public boolean recount(@RequestBody CartItem cartItem){
        try {
            cartService.increment(cartItem);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @DeleteMapping("/cart/checkOut")

    public boolean checkOut(@RequestBody Cart cart){
        try {

            cartService.checkOut(cart);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
