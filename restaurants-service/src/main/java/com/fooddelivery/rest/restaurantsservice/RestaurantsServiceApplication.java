package com.fooddelivery.rest.restaurantsservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantsServiceApplication {

	public static void main(String[] args) {

		System.out.println("system id name "+System.getenv( "AWS_ACCESS_KEY_ID"));
		System.out.println("system secret name "+System.getenv( "AWS_SECRET_ACCESS_KEY"));
		SpringApplication.run(RestaurantsServiceApplication.class, args);

		

	}

	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
		// return new ModelMapper();
	}

}
