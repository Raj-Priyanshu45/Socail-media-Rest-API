package com.rest_api.raj.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org. springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedExceptionHandle extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotException.class)
    public final ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception ex , WebRequest request) throws Exception{

        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage() , request.getDescription(false));

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("null")
    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex ,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request){

            String message = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(e-> e.getField()+" : "+e.getDefaultMessage())
                            .findFirst()
                            .orElse("Validation Failed");
            
            ErrorMessage error = new ErrorMessage(LocalDateTime.now() , message , request.getDescription(false));

            return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleotherException(Exception ex , WebRequest request) throws Exception{

        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage() , request.getDescription(false));

        return new ResponseEntity<>(error , HttpStatus.SERVICE_UNAVAILABLE);
    }

}
