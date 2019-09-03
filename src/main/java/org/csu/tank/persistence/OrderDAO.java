package org.csu.tank.persistence;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.Order;
import org.csu.tank.domain.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO {


    List<Order> getOrdersByUsername(String username);

    Order getOrderByOrderId(int orderId);  //通过Id得到Order

    List<OrderItem> getOrderItemListByOrderId(int orderId);

    void insertOrder(Order order);

    void insertOrderItem(OrderItem orderItem);

    void changeOrderStatus(int orderId);

    Order getOrderDetail(int orderId);




}
