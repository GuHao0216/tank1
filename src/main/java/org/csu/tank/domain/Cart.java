package org.csu.tank.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String username;
    private String total;
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<CartItem> getItemList() {
        return itemList;
    }


}
