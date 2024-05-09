package com.fooddelivery.rest.cartservice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    @Bean
    public RestClient restclient()
    {
        return RestClient.builder()
                 .baseUrl("http://localhost:8080/restaurants-service")
                 .build();
    }

}
