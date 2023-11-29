package com.xingon.client.thread;

import com.xingon.common.Message;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread() {
    }

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 因为这个线程需要在后台持续和服务器通信, 所以使用一个循环控制
        while (true) {
            try {
                System.out.println("客户端线程等待读取从服务器端发送的消息");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                // 如果服务器没有发送message对象, 线程会阻塞在这里, 等待读入
                Message message = (Message) objectInputStream.readObject();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
