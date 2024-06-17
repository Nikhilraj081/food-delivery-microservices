package com.fooddelivery.rest.cartservice.Model;

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
    private int numOfItem;

}
