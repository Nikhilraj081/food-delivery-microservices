package com.fooddelivery.rest.orderservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    
    public String cartItemId;
    public String foodItemId;
    public String name;
    public double price;
    public double discount;
    public double specialPrice;
    public String quantity;

}
