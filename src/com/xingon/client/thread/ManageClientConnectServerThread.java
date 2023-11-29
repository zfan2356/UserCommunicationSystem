package com.xingon.client.thread;

import java.util.HashMap;

public class ManageClientConnectServerThread {
    // 将多个线程放入一个hashMap集合中管理. key是用户id
    private static HashMap<String, ClientConnectServerThread> hashMap = new HashMap<>();

    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hashMap.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hashMap.get(userId);
    }
}
