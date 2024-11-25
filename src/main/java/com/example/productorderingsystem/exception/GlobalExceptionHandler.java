package com.example.productorderingsystem.exception;

import com.example.productorderingsystem.dto.Response;
import com.mongodb.MongoException;

import jakarta.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

   

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<Response> handleMongoException(MongoException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("MongoDB error: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidationException(ValidationException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
