package com.fooddelivery.rest.orderservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private String cartItemId;
    private String foodItemId;
    private String name;
    private double price;
    private double discount;
    private double specialPrice;
    private String quantity;

}
