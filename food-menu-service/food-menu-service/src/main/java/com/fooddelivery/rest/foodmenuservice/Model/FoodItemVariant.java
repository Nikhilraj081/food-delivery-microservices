package com.fooddelivery.rest.foodmenuservice.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemVariant {

    @NotEmpty(message = "item price should not be empty")
    private double price;

    @NotEmpty(message = "item quantity should not be empty")
    private String quantity;

    private double specialPrice;

}
