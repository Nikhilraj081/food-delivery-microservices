package com.fooddelivery.rest.restaurantsservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.restaurantsservice.Model.FoodImage;

@Repository
public interface FoodImageRepository extends MongoRepository<FoodImage, String> {


    
}
