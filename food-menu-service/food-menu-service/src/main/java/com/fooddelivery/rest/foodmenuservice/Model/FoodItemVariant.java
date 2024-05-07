package com.fooddelivery.rest.foodmenuservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemVariant {

    private double price;
    private String quantity;
    private double specialPrice;

}
