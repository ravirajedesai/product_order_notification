package com.example.Product_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Map<String,Object>>
    productGlobalExceptionHandler(ProductNotFound ex){

        Map<String,Object> response=new HashMap<>();
        response.put("TimeStamp", LocalDateTime.now());
        response.put("status",HttpStatus.NOT_FOUND.value());
        response.put("Error",HttpStatus.NOT_FOUND.name());
        response.put("Message",ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
