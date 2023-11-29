package com.xingon.client.service;

import com.xingon.client.common.Message;
import com.xingon.client.common.MessageType;
import com.xingon.client.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

// 该类完成用户登录验证和用户注册等功能
public class UserClientService {
    private User user = new User();
    private Socket socket;
    // 发送信息到服务器去验证该用户是否合法
    public boolean checkUser(String userId, String passwd) {
        boolean ok = false;
        user.setUserId(userId);
        user.setPasswd(passwd);

        try {
            // 连接到服务器
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);

            // 读取从服务端回复的Message对象
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) objectInputStream.readObject();

            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                // 这个时候要启用一个线程持有socket, 然后和服务器端进行通信
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();

                // 这里将线程放入集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                ok = true;
            } else {
                //登录失败
                socket.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ok;
    }
}
