package com.fooddelivery.rest.orderservice.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fooddelivery.rest.orderservice.Configuration.Constants;
import com.fooddelivery.rest.orderservice.Model.Cart;
import com.fooddelivery.rest.orderservice.Model.Order;
import com.fooddelivery.rest.orderservice.Model.ShippingAddress;
import com.fooddelivery.rest.orderservice.Repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestClient restClient;

    //crete order
    public Order createOrder(String userId, String addressId, String token)
    {
        Cart cart = restClient.get().uri("/cart-service/cart/user/{userId}",userId).header("Authorization", token).retrieve().body(Cart.class);

        ShippingAddress address = restClient.get().uri("/auth-service/user/{userId}/address/{addressId}",userId,addressId).header("Authorization", token).retrieve().body(ShippingAddress.class);
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setItems(cart.getCartitems());
        order.setUserId(userId);
        order.setAddress(address);
        order.setTotalPrice(cart.getTotalPrice());
        order.setDate(LocalDate.now());
        order.setTime(LocalTime.now());
        order.setDiscount(cart.getTotalDiscount());
        order.setDeliveryFee(cart.getDeliveryFee());
        order.setStatus(Constants.DEFAULT_DELIVERY_STATUS);

        Order newOrder = orderRepository.save(order);

        if(newOrder != null)
        {
            restClient.delete().uri("/cart-service/cart/{cartId}/delete/allItem",cart.getId()).header("Authorization", token).retrieve();
        }

       return order;
    }

    public List<Order> getOrderByUserId(String userId)
    {
        List<Order> order = orderRepository.findByUserId(userId);

        return order;
    }

}
