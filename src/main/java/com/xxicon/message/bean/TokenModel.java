package com.xxicon.message.bean;

import lombok.Data;

@Data
public class TokenModel {

    private int uid;
    private String token; // UUID

    public TokenModel() {
    }

    public TokenModel(int uid, String token) {
        this.uid = uid;
        this.token = token;
    }
}
