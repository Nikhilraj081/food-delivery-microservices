package com.fooddelivery.rest.restaurantsservice.Payloads;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fooddelivery.rest.restaurantsservice.Model.FoodItemVariant;
import com.fooddelivery.rest.restaurantsservice.Model.FoodReview;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemsDto {

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
    private FoodImageDto image;

}
