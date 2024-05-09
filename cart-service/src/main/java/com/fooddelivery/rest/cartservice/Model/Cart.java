package com.fooddelivery.rest.cartservice.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String id;
    private String userId;
    private double totalPrice;
    private double totalDiscount;
    private List<CartItem> cartitems;
    private double deliveryFee;

}
