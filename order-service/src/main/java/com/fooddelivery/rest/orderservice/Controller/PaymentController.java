package com.fooddelivery.rest.orderservice.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.orderservice.Service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data, @RequestHeader("Authorization") String token)
            throws RazorpayException {
        Order order = paymentService.setOrder(data, token);
        return order.toString();
    }

}
