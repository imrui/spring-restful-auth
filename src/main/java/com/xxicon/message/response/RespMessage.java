package com.xxicon.message.response;

import lombok.Data;

@Data
public class RespMessage {

    private int code;
    private String msg;
    private Object data;

    public RespMessage() {
    }

    public RespMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespMessage(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
