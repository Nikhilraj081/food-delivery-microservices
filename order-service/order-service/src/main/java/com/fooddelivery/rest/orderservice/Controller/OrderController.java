package com.fooddelivery.rest.orderservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.orderservice.Model.Order;
import com.fooddelivery.rest.orderservice.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create/user/{userId}/address/{addressId}")
    public ResponseEntity<?> createOrder(@PathVariable String userId, @PathVariable String addressId, @RequestHeader("Authorization") String token)
    {
        Order order = orderService.createOrder(userId, addressId, token);

        return ResponseEntity.created(null).body(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable String userId)
    {
        List<Order> order = orderService.getOrderByUserId(userId);

        return ResponseEntity.ok().body(order);
    }

}
