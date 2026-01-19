package com.example.exception;

import com.example.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handleOrderNotfound(OrderNotFoundException orderNotFoundException){

        String correlationId= MDC.get("CID");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse("Order Not found",
                        orderNotFoundException.getMessage(),
                        correlationId));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>  handleGeneric(Exception exception){
        String correlationId= MDC.get("CID");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse("Internal Error",
                        exception.getMessage(),
                        correlationId));
    }
}
