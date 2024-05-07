package com.fooddelivery.rest.orderservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.orderservice.Model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{


    
} 