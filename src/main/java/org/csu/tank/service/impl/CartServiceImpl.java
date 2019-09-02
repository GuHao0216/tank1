package org.csu.tank.service.impl;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.csu.tank.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public void insertCartItem(Item item) {

    }

    @Override
    public List<Item> getCartItemsByUsername(String username) {
        return null;
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {

    }

    @Override
    public void increment(CartItem cartItem) {

    }

    @Override
    public void checkOut(Cart cart) {

    }
}
