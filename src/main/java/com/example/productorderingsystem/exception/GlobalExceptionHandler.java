package com.example.productorderingsystem.exception;

import com.example.productorderingsystem.dto.Response;
import com.mongodb.MongoException;
import org.springframwork.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {

    public ResponseEntity<Response>handlerAllException(Exception ex, WebRequest request){
        Response errorResponse = Response.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("An unexpected error occurred:"+ex.getMessage())
        .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public ResponseEntity<Response>handleNotFounException(NotFoundException ex, WebRequest request){
        Response errorResponse= Response.buider()
        .status(HttpStatus.Not_Found.value())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Response>handleInvalidCredentialsException(InvalidCredentialException ex, WebRequest request){
        Response errorResponse=Response.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(ex.getMessage())
        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<Response>handleMongoException(MongoException ex,  WebRequest request){
        Response errorResponse=Response.builder()
        .status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("MongoDB error:"+ex.getMessage())
        .build();
        return new ResponseEntity<>(errorResponse, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<Response>handleValidationException(ValidationException ex, WebRequest request){
        Response errorResponse=Response.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message("Validation failed:"+ex.getMessage())
        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    
    

}
