package com.fooddelivery.rest.authorizationservice.Model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JwtRequest {

    private String email;
    private String password;

}
