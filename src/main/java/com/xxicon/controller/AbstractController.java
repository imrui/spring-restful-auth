package com.xxicon.controller;

import com.xxicon.mapper.DefaultMapper;
import com.xxicon.message.response.RespMessage;
import com.xxicon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired protected UserService userService;
    @Autowired protected DefaultMapper defaultMapper;

    protected RespMessage getRespMessage(int code, String msg) {
        return new RespMessage(code, msg);
    }

    protected RespMessage getRespMessage(int code, String msg, Object object) {
        return new RespMessage(code, msg, object);
    }

}
