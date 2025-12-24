package com.rest_api.raj.Exception;

import java.time.LocalDateTime;

public class ErrorMessage {
    
    private final LocalDateTime timeStamp;
    private final String msg;
    private final String desc;
    public ErrorMessage(LocalDateTime timeStamp , String msg , String desc){
        this.timeStamp= timeStamp;
        this.msg=msg;
        this.desc=desc;
    }

    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }

    public String getMsg(){
        return msg;
    }

    public String getDesc(){
        return desc;
    }
}
