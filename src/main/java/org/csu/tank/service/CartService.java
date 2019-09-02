package org.csu.tank.service;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;

import java.util.List;

public interface CartService {
    void insertCartItem(Item item);

    List<Item> getCartItemsByUsername(String username);

    void deleteCartItem(CartItem cartItem);

    void increment(CartItem cartItem);

    void checkOut(Cart cart);
}
