package com.fooddelivery.rest.orderservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.orderservice.Model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

    public List<Order> findByUserId(String userId);

    public Optional<Order> findById(String id);
} 