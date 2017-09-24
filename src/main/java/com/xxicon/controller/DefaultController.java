package com.xxicon.controller;

import com.xxicon.annotation.Authorization;
import com.xxicon.annotation.CurrentUser;
import com.xxicon.message.bean.LoginUserBean;
import com.xxicon.message.bean.TokenModel;
import com.xxicon.message.request.LoginReqMessage;
import com.xxicon.message.response.RespMessage;
import com.xxicon.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class DefaultController extends AbstractController {

    @PostMapping(value = "/login")
    public RespMessage login(@RequestBody LoginReqMessage req) {
        User user = this.userService.login(req.getUsername(), req.getPassword());
        if (user == null) {
            return this.getRespMessage(400, "用户名密码错误");
        }
        TokenModel model = this.userService.createToken(user);
        LoginUserBean bean = new LoginUserBean();
        bean.setUid(model.getUid());
        bean.setToken(model.getToken());
        bean.setUsername(user.getUsername());
        bean.setNickName(user.getNickName());
        bean.setCreateTime(user.getCreateTime());
        return this.getRespMessage(200, "success", bean);
    }

    @Authorization
    @DeleteMapping("/logout")
    public RespMessage logout(@CurrentUser TokenModel model) {
        this.userService.deleteToken(model.getToken());
        return this.getRespMessage(200, "success");
    }

    @GetMapping("/test")
    public RespMessage test() {
        return this.getRespMessage(200, "test content");
    }

}
