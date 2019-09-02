package org.csu.tank.persistence;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;

import java.util.List;

public interface CartDAO {


        /*
        增加item
        删除item
        增加数量
        结算
        获取购物车
        */
List<CartItem> getCartItemsByUsername(String username);

    void insertCartItem(Item item);

    void deleteCartItem(CartItem cartItem);

    void increment(CartItem cartItem);

    void checkOut(Cart cart);
}
