package com.xxicon.context;

import com.xxicon.mapper.DefaultMapper;
import com.xxicon.service.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Context {

    private static Context context;

    public Context() {
        Context.context = this;
    }

    public static Context getContext() {
        return Context.context;
    }

    @Autowired private DefaultMapper defaultMapper;
    @Autowired private UserService userService;

}
