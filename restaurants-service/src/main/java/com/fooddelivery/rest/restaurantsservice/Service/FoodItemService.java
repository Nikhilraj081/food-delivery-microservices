package com.fooddelivery.rest.restaurantsservice.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.rest.restaurantsservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItemVariant;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItems;
import com.fooddelivery.rest.restaurantsservice.Repository.FoodItemRepository;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItems> getAllItems() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItems> getItemById(String id) throws ResourceNotFoundException {
        Optional<FoodItems> items = foodItemRepository.findById(id);
        if (items == null) {
            throw new ResourceNotFoundException("item not found with id: " + id);
        }
        return items;
    }

    public FoodItems saveItems(FoodItems items) {
        List<FoodItemVariant> variant = items.getVariant();

        for (FoodItemVariant val : variant) {

            val.setSpecialPrice(val.getPrice() - items.getDiscount());
        }

        items.setVariant(variant);
        return foodItemRepository.save(items);
    }

    public FoodItems updateItems(String id, FoodItems items) throws ResourceNotFoundException {
        FoodItems foodItems = getItemById(id).get();

        if (foodItems == null) {
            throw new ResourceNotFoundException("item not found with id: " + id);
        }
        
        foodItems.setAbout(items.getAbout());
        foodItems.setCategory(items.getCategory());
        foodItems.setDiscount(items.getDiscount());
        foodItems.setName(items.getName());
        foodItems.setReview(items.getReview());
        foodItems.setType(items.getType());
        foodItems.setVariant(items.getVariant());

        List<FoodItemVariant> variant = items.getVariant();

        for (FoodItemVariant val : variant) {

            val.setSpecialPrice(val.getPrice() - items.getDiscount());
        }

        foodItems.setVariant(variant);
        return foodItemRepository.save(foodItems);
    }

}
