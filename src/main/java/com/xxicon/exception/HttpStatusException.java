package com.xxicon.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpStatusException extends Exception {

    private HttpStatus httpStatus;

    public HttpStatusException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
