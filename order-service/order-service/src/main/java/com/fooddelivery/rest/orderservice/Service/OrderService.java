package com.fooddelivery.rest.orderservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fooddelivery.rest.orderservice.Model.Cart;
import com.fooddelivery.rest.orderservice.Model.Order;
import com.fooddelivery.rest.orderservice.Repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestClient restClient;

    public Order createOrder(String userId, String addressId)
    {
        Cart cart = restClient.get().uri("/cart/user/{userId}",userId).retrieve().body(Cart.class);

        Order order = new Order();

       return order;
    }

}
