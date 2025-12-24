package com.rest_api.raj.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotException extends RuntimeException{
    
    //private String msg;
    public UserNotException(String msg){
        super(msg);
    }
}
