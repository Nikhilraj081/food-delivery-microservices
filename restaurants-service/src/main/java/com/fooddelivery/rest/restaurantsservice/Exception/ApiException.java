package com.fooddelivery.rest.restaurantsservice.Exception;
public class ApiException extends RuntimeException {

    public ApiException(String message)
    {
        super(message);
    }
}
