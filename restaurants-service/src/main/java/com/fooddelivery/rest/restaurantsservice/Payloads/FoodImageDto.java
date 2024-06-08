package com.fooddelivery.rest.restaurantsservice.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodImageDto {

    private String title;
    private byte[] image;
    
}
