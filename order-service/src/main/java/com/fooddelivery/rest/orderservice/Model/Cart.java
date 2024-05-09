package com.fooddelivery.rest.orderservice.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private String id;
    private String userId;
    private double totalPrice;
    private double totalDiscount;
    private List<CartItem> cartitems;
    private double deliveryFee;

}
