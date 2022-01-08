package com.test.warehousetest.controllers;

import com.test.warehousetest.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@Validated
public class ServiceExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception){
        log.error("ConstraintViolationException - {}", exception);
        return new ResponseEntity<String>("Not valid object. Message: "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommonException.class)
    ResponseEntity<String> handleCommonException(CommonException exception){
        log.error("CommonException. ResponseCode - {}. Message: {}.", exception.getResponseCode(), exception);
        return new ResponseEntity<String>("Not valid object. Message: "+exception.getMessage(), HttpStatus.valueOf(exception.getResponseCode()));
    }
}
