package com.xxicon.listener;

import com.xxicon.mapper.DefaultMapper;
import com.xxicon.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestfulAuthRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired private DefaultMapper defaultMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // todo: 系统启动时需要处理的逻辑
        List<User> users = this.defaultMapper.getUsers();
        for (User user : users) {
            this.logger.info(user.toString());
        }
    }

}
