package com.xxicon.exception;

import com.xxicon.message.response.RespMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespMessage handlerException(Exception e) {
        return new RespMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString());
    }

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<RespMessage> handlerHttpStatusException(HttpStatusException e) {
        HttpStatus status = e.getHttpStatus();
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(new RespMessage(status.value(), status.name()));
    }

    @ExceptionHandler(CodeMsgException.class)
    @ResponseStatus(HttpStatus.OK)
    public RespMessage handlerCodeMsgException(CodeMsgException e) {
        return new RespMessage(e.getCode(), e.getMsg());
    }

}
