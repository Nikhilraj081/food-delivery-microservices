package com.fooddelivery.rest.restaurantsservice.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItems {

    @Id
    private String id;

    @NotEmpty(message = "item name should not be empty")
    private String name;

    private double discount;

    @NotEmpty(message = "item type should not be empty")
    private String type;

    @NotEmpty(message = "item category should not be empty")
    private String category;

    private String about;
    private List<FoodItemVariant> variant;
    private List<FoodReview> review; 
}
