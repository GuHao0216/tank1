package org.csu.tank.service.impl;

import org.csu.tank.domain.Item;
import org.csu.tank.domain.Order;
import org.csu.tank.domain.OrderItem;
import org.csu.tank.persistence.ItemDAO;
import org.csu.tank.persistence.OrderDAO;
import org.csu.tank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void insertOrder(String username, int orderId, int[] itemId, int[] count, int addressId) {

        Order order = new Order();
        order.setUsername(username);
        order.setAddressId(addressId);
        order.setOrderId(orderId);
        order.setStatus(0);


        BigDecimal totalPrice = new BigDecimal(0);
        //itemDAO.getItem(itemId);
        for (int i = 0; i < itemId.length; i++) {
            //价格
            Item item = itemDAO.getItem(itemId[i]);
            BigDecimal unitPrice = item.getPrice();
            totalPrice = totalPrice.add(unitPrice.multiply(new BigDecimal(count[i])));

            //List插入
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setItemId(itemId[i]);
            orderItem.setCount(count[i]);
            orderDAO.insertOrderItem(orderItem);
        }
        order.setTotal(totalPrice);
        orderDAO.insertOrder(order);
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
