package com.xxicon.message.bean;

import lombok.Data;

import java.util.Date;

@Data
public class LoginUserBean {

    private int uid;
    private String token; // UUID
    private String username;
    private String nickName;
    private Date createTime;

}
