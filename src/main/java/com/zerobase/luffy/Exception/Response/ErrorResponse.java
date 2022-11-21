package com.zerobase.luffy.Exception.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@Slf4j
public class ErrorResponse extends RuntimeException{

    private HttpStatus status;

    private String message;

    public ErrorResponse(HttpStatus status, String message){
        this.message=message;
        this.status=status;
    }


}
