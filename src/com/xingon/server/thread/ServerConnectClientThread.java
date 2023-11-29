package com.xingon.server.thread;

import com.xingon.common.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread {
    private Socket socket = null;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("服务端和客户端 " + userId + " 保持通信...");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) objectInputStream.readObject();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
