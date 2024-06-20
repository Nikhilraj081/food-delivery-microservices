package com.fooddelivery.rest.orderservice.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.orderservice.Model.Order;
import com.fooddelivery.rest.orderservice.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/user/{userId}/address/{addressId}/orderId/{orderId}")
    public ResponseEntity<?> createOrder(@PathVariable String userId, @PathVariable String addressId,
            @RequestHeader("Authorization") String token, @PathVariable("orderId") String orderId) {
        Order order = orderService.createOrder(userId, addressId, token, orderId);

        return ResponseEntity.created(null).body(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable String userId) {
        List<Order> order = orderService.getOrderByUserId(userId);
        System.out.println("get the order data");
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("update_order/{orderId}/payment/{paymentId}/paymentStatus/{paymentStatus}/orderStatus/{orderStatus}")
    public ResponseEntity<?> updateOrder(@PathVariable String orderId, @PathVariable String paymentId, @PathVariable String paymentStatus, @PathVariable String orderStatus)
    {
        
       Order order = orderService.updateOrder(orderId,paymentId,paymentStatus,orderStatus);

       return ResponseEntity.accepted().body(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean deleteOrder(@PathVariable("orderId") String orderId)
    {
        return orderService.deleteOrder(orderId);
    }

}
