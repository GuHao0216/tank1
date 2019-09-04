package org.csu.tank.controller;

import org.csu.tank.domain.Item;
import org.csu.tank.domain.Order;
import org.csu.tank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/item/checkOut")
    public boolean checkOut(@RequestParam int itemId, @RequestParam String username, @RequestParam int addressId, @RequestParam int count){
        try {
            int [] itemId1 = new int[1];
            int [] count1 = new int[1];
            itemId1[0] = itemId;
            count1[0] = count;
            int orderId =(int) (Math.random()*100000000);
            orderService.insertOrder(username,orderId,itemId1,count1,addressId);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable int orderId){
        return orderService.getOrder(orderId);
    }

    @GetMapping("/getOrder/{username}")
    public List<Order> getOrdersByUsername(@PathVariable String username){
        return orderService.getOrdersByUsername(username);
    }
    @GetMapping("/orderDetail/{orderId}")
    public Order getOrderDetail(@PathVariable int orderId){
        return orderService.getOrderDetail(orderId);
    }

    @GetMapping("/getStatusCount/{username}/{status}")
    public int getStatusCount(@PathVariable String username,@PathVariable int status){
        return orderService.getStatusCount(username,status);
    }
    @GetMapping("/getOrdersByStatus/{status}")
    public List<Order> getOrdersByStatus(@PathVariable int status){
        return orderService.getOrdersByStatus(status);
    }
    @PutMapping("/changeOrderStatus/{orderId}/{status}")
    public boolean changeOrderStatus(@PathVariable int orderId,@PathVariable int status) {
        try {
            orderService.changeOrderStatus(orderId,status);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
