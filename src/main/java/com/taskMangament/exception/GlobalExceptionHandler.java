/*
package com.taskMangament.exception;


import com.taskMangament.model.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        APIResponse errorDetails = new APIResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date(),  request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
        APIResponse errorDetails = new APIResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),new Date(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
        APIResponse errorDetails = new APIResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), new Date(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(value = {InvalidJwtAuthenticationException.class})
    public ResponseEntity<?> invalidJwtAuthentication(InvalidJwtAuthenticationException ex, WebRequest request) {
        APIResponse errorDetails = new APIResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),new Date(), request.getDescription(false));
        logger.error("Auth GlobalExceptionHandler, throw exception InvalidJwtAuthenticationException {} ", errorDetails.toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        APIResponse errorDetails = new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date(), request.getDescription(false));
        logger.error("Auth GlobalExceptionHandler, not handled exception {} ", errorDetails.toString());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }
}
*/
