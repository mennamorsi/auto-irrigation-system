package com.irrigation.system.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException{
    private HttpStatus httpCode;
    public ApiException(HttpStatus httpCode, String message) {
        super(message);
        this.httpCode= httpCode;
    }
}
