package com.plataform.api.application.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends Exception {

    private HttpStatus status;
    private String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
