package com.fooddelivery.rest.orderservice.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;
    private String userId;
    private String status;
    private ShippingAddress address;
    private double totalPrice;
    private double deliveryFee;
    private double discount;
    private List<CartItem> items;
    private LocalDate date;
    private LocalTime time;

}
