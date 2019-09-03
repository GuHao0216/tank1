package org.csu.tank.persistence;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDAO {


        /*
        增加item
        删除item
        增加数量
        结算
        获取购物车
        */
    List<CartItem> getCartItemsByUsername(String username);

    Cart getCartByUsername(String username);

    void insertCartItem(CartItem cartItem);

    void deleteCartItem(CartItem cartItem);

    void increment(CartItem cartItem);

    void checkOut(Cart cart);
}
