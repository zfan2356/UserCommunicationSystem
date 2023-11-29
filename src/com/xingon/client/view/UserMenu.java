package com.xingon.client.view;

import java.util.Scanner;

public class UserMenu {
    private String userId;
    private Scanner scanner = new Scanner(System.in);
    public UserMenu(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int show() {
        System.out.println("============网络通信二级菜单(用户 " + userId + " )============");
        System.out.println("\t\t 1 显示在线用户列表");
        System.out.println("\t\t 2 群发消息");
        System.out.println("\t\t 3 私聊消息");
        System.out.println("\t\t 4 发送文件");
        System.out.println("\t\t 5 回到一级菜单");
        System.out.println("\t\t 9 退出系统");
        System.out.print("请输入你的选择: ");

        String oper = scanner.next();
        switch (oper) {
            case "1":
                System.out.println("显示在线用户列表");
                return 1;
            case "2":
                return 1;
            case "3":
                return 1;
            case "4":
                return 1;
            case "5":
                return 2;
            case "9":
                return 0;
            default:
                System.out.println("输入信息有误, 请重新输入");
                return 1;
        }
    }
}
