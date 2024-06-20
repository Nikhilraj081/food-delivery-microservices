package com.fooddelivery.rest.restaurantsservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(Constant.AWS_ACCESS_KEY_ID, Constant.AWS_SECRET_ACCESS_KEY);

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("eu-north-1"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}
