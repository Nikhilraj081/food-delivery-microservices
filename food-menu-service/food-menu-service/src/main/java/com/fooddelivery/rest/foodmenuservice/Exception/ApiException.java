package com.fooddelivery.rest.foodmenuservice.Exception;

public class ApiException extends RuntimeException {

    public ApiException(String message)
    {
        super(message);
    }
}
