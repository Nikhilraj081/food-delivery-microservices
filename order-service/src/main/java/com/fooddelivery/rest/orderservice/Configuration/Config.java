package com.fooddelivery.rest.orderservice.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    @Value("${baseurl}")
    private String baseurl;

    @Bean
    public RestClient restClient()
    {
        return RestClient.builder()
                .baseUrl(baseurl)
                .build();
    }

}
