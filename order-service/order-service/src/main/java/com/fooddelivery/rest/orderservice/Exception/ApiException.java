package com.fooddelivery.rest.orderservice.Exception;

public class ApiException extends RuntimeException {

    public ApiException(String message)
    {
        super(message);
    }
}
