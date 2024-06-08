package com.fooddelivery.rest.restaurantsservice.Payloads;

import java.util.List;

import com.fooddelivery.rest.restaurantsservice.Model.FoodImage;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItemVariant;
import com.fooddelivery.rest.restaurantsservice.Model.FoodReview;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private String id;
    private String name;
    private double discount;
    private String type;
    private String category;
    private String about;
    private List<FoodItemVariant> variant;
    private List<FoodReview> review; 
    private String image;
    
}

