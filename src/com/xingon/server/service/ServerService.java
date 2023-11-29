package com.xingon.server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.xingon.common.Message;
import com.xingon.common.MessageType;
import com.xingon.common.User;
import com.xingon.server.database.UserData;
import com.xingon.server.thread.ManageServerConnectClientThread;
import com.xingon.server.thread.ServerConnectClientThread;

public class ServerService {
    private ServerSocket serverSocket = null;

    public ServerService() {
        System.out.println("服务端正在9999端口监听");
        try {
            serverSocket = new ServerSocket(9999);

            while (true) { // 当和某个客户端建立连接后, 会继续监听, 如果没有客户端连接, 会阻塞
                Socket accept = serverSocket.accept();

                ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                User user = (User) objectInputStream.readObject();

                Message message = new Message();
                // 验证用户
                if (UserData.findUser(user.getUserId(), user.getPasswd())) {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    objectOutputStream.writeObject(message);
                    // 创建一个线程, 和客户端保持通信
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(accept, user.getUserId());
                    serverConnectClientThread.start();

                    // 将线程对象放入集合中进行管理
                    ManageServerConnectClientThread.addServerConnectClientThread(user.getUserId(), serverConnectClientThread);

                } else {
                    // 登录失败
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAILED);
                    objectOutputStream.writeObject(message);
                    accept.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 如果服务端退出while循环, 说明不再监听, 这时要关闭资源
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
