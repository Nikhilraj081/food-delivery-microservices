package com.fooddelivery.rest.cartservice.Model;

import jakarta.annotation.Generated;
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
    public String quantity;

}
