package com.joaoreche.api_produto.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaoreche.api_produto.exception.ResourceNotFoundException;
import com.joaoreche.api_produto.model.error.ErrorMessage;

@RestControllerAdvice 
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorMessage error = new ErrorMessage("Not Found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
