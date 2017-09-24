package com.xxicon.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String nickName;
    private int type;
    private int status;
    private Date createTime;
    private Date updateTime;

}
