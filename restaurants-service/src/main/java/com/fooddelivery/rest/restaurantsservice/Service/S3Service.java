package com.fooddelivery.rest.restaurantsservice.Service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fooddelivery.rest.restaurantsservice.Config.Constant;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    public String uploadImage(MultipartFile image, String fileName) throws IOException {

        

        // Upload the image to S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(Constant.BUCKET_NAME)
                .key(Constant.BUCKET_Folder + fileName)
                .acl("public-read") // Make the file publicly accessible
                .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(image.getBytes()));

        // Construct the file URL
        URL url = s3Client.utilities().getUrl(builder -> builder.bucket(Constant.BUCKET_NAME).key(fileName));
        return url.toString();
    }
}

