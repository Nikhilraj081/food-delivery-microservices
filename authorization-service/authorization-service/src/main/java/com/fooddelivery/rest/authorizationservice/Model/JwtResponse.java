package com.fooddelivery.rest.authorizationservice.Model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JwtResponse {

    private String jwtToken;
    private String userId;
    private String userName;

}
