package com.fooddelivery.rest.cartservice.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    @Value("${baseurl}")
    private String baseurl;

    @Bean
    public RestClient restclient()
    {
        return RestClient.builder()
                 .baseUrl(baseurl)
                 .build();
    }

}
