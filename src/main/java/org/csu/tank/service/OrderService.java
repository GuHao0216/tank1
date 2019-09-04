package org.csu.tank.service;

import org.csu.tank.domain.Order;

import java.util.List;

public interface OrderService {
    void insertOrder(String username,int OrderId,int[] itemId,int[] count,int addressId);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    Order getOrderDetail(int orderId);

    List<Order> getOrdersByStatus(int status);

    int getStatusCount(String username,int status);

    void changeOrderStatus(int orderId,int status);
}
