package com.fooddelivery.rest.authorizationservice.Exception;

public class ApiException extends RuntimeException {

    public ApiException(String message)
    {
        super(message);
    }
}
