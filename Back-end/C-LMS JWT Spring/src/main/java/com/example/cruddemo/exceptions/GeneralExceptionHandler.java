package com.example.cruddemo.exceptions;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cruddemo.entity.dtos.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

//ControllerAdvice is a global @ExceptionHandler 
@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

   
    @Autowired
    public GeneralExceptionHandler(MessageSource messageSource) {
    }
     //without localization
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorDetails> handleBusinessException(BusinessException ex, WebRequest request) {
        log.warn(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        if (ex.getParams() != null) {
            errorDetails.setParamObjects(ex.getParams());
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(NoDataFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNoDataFoundException(NoDataFoundException ex, WebRequest request) {
        log.warn(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    @Override  
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
    {  
        ErrorDetails errorDetails = new ErrorDetails(new Date(),  "Validation Failed", ex.getBindingResult().toString());  
        //returning exception structure and specific status   
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);  
    }  
}