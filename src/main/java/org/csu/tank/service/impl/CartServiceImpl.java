package org.csu.tank.service.impl;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.CartItem;
import org.csu.tank.domain.Item;
import org.csu.tank.persistence.CartDAO;
import org.csu.tank.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public Cart getCartByUsername(String username) {
        Cart cart = cartDAO.getCartByUsername(username);
        List<CartItem> cartItemList = cartDAO.getCartItemsByUsername(username);
        cart.setCartItemList(cartItemList);
        return cart;
    }

    @Override
    public void insertCartItem(Item item,int cartId,int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setItemId(item.getItemId());
        cartItem.setCount(count);
        cartDAO.insertCartItem(cartItem);
    }

    @Override
    public List<CartItem> getCartItemsByUsername(String username) {
        List<CartItem> cartItemList = cartDAO.getCartItemsByUsername(username);
        return cartItemList;
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartDAO.deleteCartItem(cartItem);
    }

    @Override
    public void increment(CartItem cartItem) {
        cartDAO.increment(cartItem);
    }

    @Override
    public void checkOut(Cart cart) {
        cartDAO.checkOut(cart);
    }
}
