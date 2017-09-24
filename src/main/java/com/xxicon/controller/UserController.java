package com.xxicon.controller;

import com.xxicon.annotation.Authorization;
import com.xxicon.exception.HttpStatusException;
import com.xxicon.message.response.RespMessage;
import com.xxicon.model.User;
import com.xxicon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Authorization
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list() {
        return this.defaultMapper.getUsers();
    }

    @PostMapping
    public User add(@RequestBody User user) throws Exception {
        // todo 参数合法性检测 密码加密
        this.defaultMapper.insertUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) throws Exception {
        User user = this.defaultMapper.getUser(id);
        if (user == null) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @RequestBody User user) throws Exception {
        // todo 参数合法性检测 密码加密
        if (id != user.getId()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST);
        }
        this.defaultMapper.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public RespMessage delete(@PathVariable int id) throws Exception {
        User user = this.defaultMapper.getUser(id);
        if (user == null) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST);
        }
        //超级管理员帐号不能删除
        if (user.getUsername().equalsIgnoreCase("admin")) {
            return this.getRespMessage(400, "cannot delete");
        }
        int res = this.defaultMapper.deleteUser(id);
        if (res <= 0) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.getRespMessage(200, "success");
    }

}
