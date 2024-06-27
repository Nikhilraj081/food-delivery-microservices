package com.fooddelivery.rest.orderservice.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fooddelivery.rest.orderservice.Configuration.Constants;
import com.fooddelivery.rest.orderservice.Exception.ApiException;
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

    // crete order
    public Order createOrder(String userId, String addressId, String token, String orderId) {
        Cart cart = restClient.get().uri("/cart-service/cart/user/{userId}", userId).header("Authorization", token)
                .retrieve().body(Cart.class);

        ShippingAddress address = restClient.get()
                .uri("/auth-service/user/{userId}/address/{addressId}", userId, addressId)
                .header("Authorization", token).retrieve().body(ShippingAddress.class);

        Order order = new Order();
        order.setId(orderId);
        order.setItems(cart.getCartitems());
        order.setUserId(userId);
        order.setAddress(address);
        order.setTotalPrice(cart.getTotalPrice());
        order.setDate(indianTimeAndDate().get("date"));
        order.setTime(indianTimeAndDate().get("time"));
        order.setDiscount(cart.getTotalDiscount());
        order.setDeliveryFee(cart.getDeliveryFee());
        order.setStatus(Constants.DEFAULT_DELIVERY_STATUS);
        order.setActive(Constants.DEFAULT_ACTIVE_STATUS);

        Order newOrder = orderRepository.save(order);
        return newOrder;
    }

    // To get order by user id
    public List<Order> getOrderByUserId(String userId) {
        List<Order> order = orderRepository.findByUserId(userId);
        Collections.reverse(order);
        return order;
    }

    // To update order
    public Order updateOrder(String orderId, String paymentId, String paymentStatus, String orderstatus) {

        Order order = orderRepository.findById(orderId).get();
        order.setActive(true);
        order.setPaymentId(paymentId);
        order.setPaymentStatus(paymentStatus);
        order.setStatus(orderstatus);

        Order newOrder = orderRepository.save(order);

        if (newOrder != null) {
            return newOrder;
        }

        throw new ApiException("Order is not updated");
    }

    // to delete order
    public boolean deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return true;
    }

    public Map<String, String> indianTimeAndDate() {

        Map<String, String> dateAndTime = new HashMap<String, String>();

        // Define the time zone
        ZoneId indianZoneId = ZoneId.of(Constants.TIME_ZONE_CODE);

        // Get the current date and time in the Indian time zone
        ZonedDateTime indianTime = ZonedDateTime.now(indianZoneId);

        // Format the date and time separately
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT);

        // Extract date and time as separate strings
        String date = indianTime.format(dateFormatter);
        String time = indianTime.format(timeFormatter);

        // Store date and time in the map
        dateAndTime.put("date", date);
        dateAndTime.put("time", time);

        return dateAndTime;
    }

}
