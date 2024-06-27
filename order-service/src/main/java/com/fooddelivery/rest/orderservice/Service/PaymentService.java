package com.fooddelivery.rest.orderservice.Service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.rest.orderservice.Configuration.Constants;
import com.fooddelivery.rest.orderservice.Exception.ApiException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

    @Autowired
    private OrderService orderService;

    public Order setOrder(Map<String, Object> data, String token) throws RazorpayException {

        double amount = Double.parseDouble(data.get("amount").toString());

        RazorpayClient razorpayClient = new RazorpayClient(Constants.PAYMENT_KEY_ID, Constants.PAYMENT_KEY_SECRET);

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", amount * 100); // multiply by 100 to change in paisa
        orderRequest.put("currency", Constants.CURRENCY);
        orderRequest.put("receipt", Constants.RECEIPT);

        Order order = razorpayClient.orders.create(orderRequest);

        if (order != null) {
            double orderAmount = orderService.createOrder(data.get("userId").toString(),
                    data.get("addressId").toString(), token, order.get("id")).getTotalPrice();
            if (orderAmount == amount) {
                return order;
            }
            orderService.deleteOrder(order.get("id"));
            throw new ApiException("Your cart item has been changed, please check and try again");
        }
        throw new ApiException("Order is not created");
    }
}
