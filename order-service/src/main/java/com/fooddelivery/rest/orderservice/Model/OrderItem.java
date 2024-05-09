package com.fooddelivery.rest.orderservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String id;
    private String name;
    private String quantity;
    private String price;
    private int piece;

}
