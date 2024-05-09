package com.fooddelivery.rest.restaurantsservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.restaurantsservice.Model.FoodItems;


@Repository
public interface FoodItemRepository extends MongoRepository<FoodItems, String>{

    
} 