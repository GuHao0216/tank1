package org.csu.tank.service.impl;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.csu.tank.domain.Account;
import org.csu.tank.domain.Item;
import org.csu.tank.domain.Order;
import org.csu.tank.domain.OrderItem;
import org.csu.tank.persistence.AccountDAO;
import org.csu.tank.persistence.ItemDAO;
import org.csu.tank.persistence.OrderDAO;
import org.csu.tank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void insertOrder(String username, int orderId, int[] itemId, int[] count, int addressId) {

        Order order = new Order();
        order.setUsername(username);
        order.setAddressId(addressId);
        order.setOrderId(orderId);
        order.setStatus(0);
        BigDecimal totalPrice = new BigDecimal(0);
        for (int i = 0; i < itemId.length; i++) {
            Item item = itemDAO.getItem(itemId[i]);
            BigDecimal unitPrice = item.getPrice();
            totalPrice = totalPrice.add(unitPrice.multiply(new BigDecimal(count[i])));
        }
        order.setTotal(totalPrice);
        orderDAO.insertOrder(order);

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

    }

    @Override
    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        order.setOrderItemList(orderDAO.getOrderItemListByOrderId(orderId));
        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orderList = orderDAO.getOrdersByUsername(username);
        for (int i =0; i<orderList.size(); i++){
            Order order = orderList.get(i);
            order.setOrderItemList(orderDAO.getOrderItemListByOrderId(order.getOrderId()));
            orderList.set(i,order);
        }
        return orderList;
    }

    @Override
    public JSONObject getOrderDetail(int orderId) {
        JSONObject jsonObject = new JSONObject();
        Order order = orderDAO.getOrderDetail(orderId);
        order.setOrderItemList(orderDAO.getOrderItemListByOrderId(orderId));
        Account account = accountDAO.getAddress(order.getUsername(),order.getAddressId());
        jsonObject.put("order",order);
        jsonObject.put("account",account);
        return jsonObject;
    }

    @Override
    public List<Order> getOrdersByStatus(int status) {
        List<Order> orderList = orderDAO.getOrdersByStatus(status);
        for (int i =0; i<orderList.size(); i++){
            Order order = orderList.get(i);
            order.setOrderItemList(orderDAO.getOrderItemListByOrderId(order.getOrderId()));
            orderList.set(i,order);
        }
        return orderList;
    }

    @Override
    public int getStatusCount(String username, int status) {
        return orderDAO.getStatusCount(username,status);

    }

    @Override
    public void changeOrderStatus(int orderId,int status) {
        orderDAO.changeOrderStatus(orderId,status);
    }
}
