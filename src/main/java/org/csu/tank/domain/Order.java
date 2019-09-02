package org.csu.tank.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int status;
    private String username;
    private String total;
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
