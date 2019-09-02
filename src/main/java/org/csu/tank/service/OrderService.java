package org.csu.tank.service;

import org.csu.tank.domain.Order;

import java.util.List;

public interface OrderService {
    void insertOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);
}
