package com.fooddelivery.rest.orderservice.Model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

    @Id
    private String id;
    private String userName;
    private String address;
    private String pinCode;
    private String state;
    private String city;
    private String landMark;

}
