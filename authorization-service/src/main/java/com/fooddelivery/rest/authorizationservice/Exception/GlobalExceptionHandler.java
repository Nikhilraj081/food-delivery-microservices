package com.fooddelivery.rest.authorizationservice.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fooddelivery.rest.authorizationservice.Paylods.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiExceptionHandler(ApiException ex)
    {
        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> BadRequestException(BadRequestException ex)
    {
        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse> MalformedJwtException(MalformedJwtException ex)
    {
        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse> ExpiredJwtException(ExpiredJwtException ex)
    {
        ApiResponse apiResponse = new ApiResponse("Auth token is expired please generate a new token",false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationException(ConstraintViolationException ex)
    {
        // Map<String, String> response = new HashMap<>();
        ApiResponse response = new ApiResponse();
         ex.getConstraintViolations().forEach(error -> {
            String fieldName = error.getPropertyPath().toString();
            String message = error.getMessageTemplate(); 
            response.setMessage(message);
            response.setStatus(false);
         });

        return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        // Map<String, String> response = new HashMap<>();
        ApiResponse response = new ApiResponse();
        ex.getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            response.setMessage(message);
            response.setStatus(false);
         });
        return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
    }

   
}