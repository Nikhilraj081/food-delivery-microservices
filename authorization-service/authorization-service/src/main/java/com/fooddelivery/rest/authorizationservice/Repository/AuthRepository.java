package com.fooddelivery.rest.authorizationservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.authorizationservice.Model.User;

@Repository
public interface AuthRepository extends MongoRepository<User, String> {

    
} 
