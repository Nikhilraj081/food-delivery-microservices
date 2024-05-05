package com.fooddelivery.rest.foodmenuservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.rest.foodmenuservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.foodmenuservice.Model.FoodItems;
import com.fooddelivery.rest.foodmenuservice.Repository.FoodItemRepository;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepository foodItemRepository;


    public List<FoodItems> getAllItems()
    {
       return foodItemRepository.findAll();
    }

    public Optional<FoodItems> getItemById(String id) throws ResourceNotFoundException
    {
        Optional<FoodItems> items = foodItemRepository.findById(id);
        if(items == null)
        {
            throw new ResourceNotFoundException("item not found with id: "+id);
        }
        return items;
    }

    public FoodItems saveItems(FoodItems items)
    {
        return foodItemRepository.save(items);
    }

    public FoodItems updateItems(String id, FoodItems items) throws ResourceNotFoundException
    {
        Optional<FoodItems> foodItems = getItemById(id);

        if(foodItems == null)
        {
            throw new ResourceNotFoundException("item not found with id: "+id);
        }
        return foodItemRepository.save(items);
    }

}
