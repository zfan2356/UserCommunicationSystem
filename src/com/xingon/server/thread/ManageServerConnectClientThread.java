package com.xingon.server.thread;

import java.util.HashMap;

// 用于管理和客户端进行通信的线程
public class ManageServerConnectClientThread {
    private static HashMap<String, ServerConnectClientThread> hashMap = new HashMap<>();

    // 添加线程对象到集合
    public static void addServerConnectClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hashMap.put(userId, serverConnectClientThread);
    }

    // 根据userId返回线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hashMap.get(userId);
    }
}
