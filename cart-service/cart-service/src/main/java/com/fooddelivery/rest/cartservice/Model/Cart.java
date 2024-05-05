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
    public String id;
    public String userId;
    public double totalPrice;
    public double totalDiscount;
    public List<CartItem> cartitems;

}
