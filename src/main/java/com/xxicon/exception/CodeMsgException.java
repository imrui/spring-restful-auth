package com.xxicon.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CodeMsgException extends Exception {

    private int code;
    private String msg;

    public CodeMsgException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
