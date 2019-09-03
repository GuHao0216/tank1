package org.csu.tank.service.impl;

import org.csu.tank.domain.Order;
import org.csu.tank.persistence.OrderDAO;
import org.csu.tank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private  OrderDAO orderDAO;

    @Override
    public void insertOrder(Order order) {

    }

    @Override
    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        order.setItemList(orderDAO.getOrderItemListByOrderId(orderId));
        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return null;
    }
}
