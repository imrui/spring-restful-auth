package com.xxicon.service;

import com.xxicon.mapper.DefaultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class AbstractService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected RedisTemplate redisTemplate;
    @Autowired
    protected DefaultMapper defaultMapper;

}
