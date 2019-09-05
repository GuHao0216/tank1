package org.csu.tank.controller;

import com.alibaba.fastjson.JSONObject;
import org.csu.tank.base.Response;
import org.csu.tank.domain.Item;
import org.csu.tank.domain.Order;
import org.csu.tank.service.OrderService;
import org.csu.tank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.csu.tank.base.Response.success;
import static org.csu.tank.base.Response.fail;

@RestController
@CrossOrigin(allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/item/checkOut")
    public Response checkOut(@RequestParam int itemId, @RequestParam int addressId, @RequestParam int count,HttpServletRequest request){
        try {
            int [] itemId1 = new int[1];
            int [] count1 = new int[1];
            itemId1[0] = itemId;
            count1[0] = count;
            int orderId =(int) (Math.random()*100000000);
            String username = JwtUtil.getUsernameByToken(request.getHeader("token"));
            orderService.insertOrder(username,orderId,itemId1,count1,addressId);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return success(object);
        }
    }

    @GetMapping("/order/{orderId}")
    public Response getOrder(@PathVariable int orderId){
        JSONObject object = new JSONObject();
        object.put("order",orderService.getOrder(orderId));
        return success(object);
    }

    @GetMapping("/order")
    public Response getOrdersByUsername(HttpServletRequest request){
        String tokenName = JwtUtil.getUsernameByToken(request.getHeader("token"));
        JSONObject object = new JSONObject();
        object.put("orderList",orderService.getOrdersByUsername(tokenName));
        return success(object);
    }

    /*
    * 获取商品订单详情：
    * 订单状态
    * 收件人+收件地址+收件电话
    * OrderItemList
    * order总价
    *
    * 之前的orderDAO需要修改，需要设置orderItemList
    * */
    @GetMapping("/orderDetail/{orderId}")
    public Response getOrderDetail(@PathVariable int orderId){
        JSONObject object = new JSONObject();
        object.put("detail",orderService.getOrderDetail(orderId));
        return success(object);
    }

    @GetMapping("/getStatusCount/{status}")
    public Response getStatusCount(HttpServletRequest request,@PathVariable int status){
        String tokenName = JwtUtil.getUsernameByToken(request.getHeader("token"));
        JSONObject object = new JSONObject();
        object.put("count",orderService.getStatusCount(tokenName,status));
        return success(object);
    }

    @GetMapping("/getOrdersByStatus/{status}")
    public Response getOrdersByStatus(@PathVariable int status){
        JSONObject object = new JSONObject();
        object.put("orderList",orderService.getOrdersByStatus(status));
        return success(object);
    }

    @PutMapping("/changeOrderStatus/{orderId}/{status}")
    public Response changeOrderStatus(@PathVariable int orderId,@PathVariable int status) {
        try {
            orderService.changeOrderStatus(orderId,status);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        }catch (Exception e){
            return fail("改变状态失败！");
        }

    }

}
