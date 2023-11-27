package com.xingon.Server.common;

import java.io.Serializable;

// 表示一个客户信息
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId; // 用户id
    private String passwd; // 用户密码


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
