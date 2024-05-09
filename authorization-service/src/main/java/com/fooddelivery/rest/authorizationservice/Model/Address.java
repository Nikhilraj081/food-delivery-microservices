package com.fooddelivery.rest.authorizationservice.Model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  
    @Id
    private String id;
    private String userName;
    private String address;
    private String pinCode;
    private String state;
    private String city;
    private String landMark;
}