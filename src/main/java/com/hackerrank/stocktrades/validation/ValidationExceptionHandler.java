package com.hackerrank.stocktrades.validation;

import com.hackerrank.stocktrades.model.exception.BadRequestException;
import com.hackerrank.stocktrades.model.exception.DataNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exception occurred! " + ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage() + "\n");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage() + "\n");
        }
        return new ResponseEntity("INPUT VALIDATION ERROR!! "+  errors,
                headers, HttpStatus.BAD_REQUEST  );
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity("METHOD not allowed exception "+ex,
                headers, HttpStatus.METHOD_NOT_ALLOWED  );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if ( ex instanceof BadRequestException ){
                return new ResponseEntity<>("BAD_REQUEST_ERROR!! "+ex.getMessage() , headers, HttpStatus.BAD_REQUEST);
        }else  if ( ex instanceof DataNotFoundException){
            return new ResponseEntity<>("DATA_NOT_FOUND_ERROR!! "+ex.getMessage() , headers, HttpStatus.NOT_FOUND );
        }else{
            return new ResponseEntity<>("Internal exception "+ ex.getMessage() , headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
