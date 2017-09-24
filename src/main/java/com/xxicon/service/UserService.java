package com.xxicon.service;

import com.xxicon.base.RedisConstant;
import com.xxicon.message.bean.TokenModel;
import com.xxicon.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends AbstractService {

    @Value("${user.token.expire.minutes}")
    private long tokenExpireMinutes;

    public List<User> getUsers() {
        return this.defaultMapper.getUsers();
    }

    public User getUser(int id) {
        return this.defaultMapper.getUser(id);
    }

    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }
        User user = this.defaultMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equalsIgnoreCase(password)) {
            return null;
        }
        return user;
    }

    private String getTokenKey(String token) {
        return MessageFormat.format(RedisConstant.TOKEN_KEY, token);
    }

    public TokenModel createToken(User user) {
        String token = user.getId() + "-" + UUID.randomUUID().toString();
        TokenModel model = new TokenModel(user.getId(), token);
        ValueOperations<String, TokenModel> opsForValue = this.redisTemplate.opsForValue();
        opsForValue.set(this.getTokenKey(token), model, this.tokenExpireMinutes, TimeUnit.MINUTES);
        return model;
    }

    public TokenModel checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        ValueOperations<String, TokenModel> opsForValue = this.redisTemplate.opsForValue();
        String tokenKey = this.getTokenKey(token);
        TokenModel model = opsForValue.get(tokenKey);
        if (model == null) {
            return null;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        this.redisTemplate.expire(tokenKey, this.tokenExpireMinutes, TimeUnit.MINUTES);
        return model;
    }

    public void deleteToken (String token) {
        this.redisTemplate.delete(this.getTokenKey(token));
    }

}
