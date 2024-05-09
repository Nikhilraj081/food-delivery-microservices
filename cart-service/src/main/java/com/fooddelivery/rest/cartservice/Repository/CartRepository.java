package com.fooddelivery.rest.cartservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.rest.cartservice.Model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    public Cart findCartByUserId(String id);

}
