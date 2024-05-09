package com.fooddelivery.rest.cartservice.Model;

import java.util.List;

import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItems {

    @Id
    private String id;
    private String name;
    private double discount;
    private String type;
    private String category;
    private String about;
    private List<FoodItemVariant> variant;
    private List<FoodReview> review;
}
