package org.csu.tank.service;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;

import java.util.List;

public interface CartService {

    Cart getCartByUsername(String username);

    void insertCartItem(Item item,int cartId,int count);

    List<CartItem> getCartItemsByUsername(String username);

    void deleteCartItem(CartItem cartItem);

    void increment(CartItem cartItem);

    void checkOut(CartItem cartItem);
}
