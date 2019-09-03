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
        for(int i=0;i<order.getItemList().size();i++){
           int itemId = order.getItemList().get(i).getItemId();
           int count = order.getItemList().get(i).getCount();
            orderDAO.insertOrder(itemId,count);
        }


    }

    @Override
    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        order.setItemList(orderDAO.getOrderItemListByOrderId(orderId));
        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }
}
