package com.fooddelivery.rest.foodmenuservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.foodmenuservice.Model.FoodItems;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItems, String>{

    
} 