package org.csu.tank.persistence;

import org.csu.tank.domain.Cart;
import org.csu.tank.domain.Order;
import org.csu.tank.domain.OrderItem;

import java.util.List;

public interface OrderDAO {


    List<OrderItem> getOrdersByUsername(String username);

    Order getOrderByOrderId(int orderId);  //通过Id得到Order

    List<OrderItem> getOrderItemListByOrderId(int orderId);

    void insertOrder(int itemId, int count);

//    void insertOrder(Cart cart);

    void changeOrderStatus(int orderId);

    Order getOrderDetail(int orderId);

//    void insertCartItem(CartItem cartItem);
//
//    void deleteCartItem(CartItem cartItem);
//
//    void increment(CartItem cartItem);


}
