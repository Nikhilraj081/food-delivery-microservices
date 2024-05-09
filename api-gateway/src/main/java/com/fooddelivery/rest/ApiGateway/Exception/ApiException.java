package com.fooddelivery.rest.ApiGateway.Exception;

public class ApiException extends RuntimeException {

    public ApiException(String message, boolean status) {
        super(message);
    }
}
