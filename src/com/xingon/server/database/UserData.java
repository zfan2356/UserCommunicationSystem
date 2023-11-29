package com.xingon.server.database;

import com.xingon.common.User;

import java.util.HashMap;

public class UserData {
    private static HashMap<String, User> DataBase = new HashMap<>();

    static {
        //在静态代码块初始化一些用户
        DataBase.put("001", new User("001", "123456"));
        DataBase.put("002", new User("002", "123456"));
        DataBase.put("003", new User("003", "123456"));
        DataBase.put("004", new User("004", "123456"));
    }

    public static boolean findUser(String userId, String passwd) {
        User user = DataBase.get(userId);
        if (user == null || user.getPasswd().equals(passwd)) {
            return false;
        }
        return true;
    }
}
