package com.fooddelivery.rest.restaurantsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodReview {

    private String userId;
    private String review;
    private int rating;

}
