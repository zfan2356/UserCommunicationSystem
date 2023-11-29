package com.xingon.client.view;

import com.xingon.client.service.UserClientService;

import java.util.Scanner;

public class MainView {
    private boolean loop = true; // 控制是否显示菜单
    private UserClientService userClientService = new UserClientService(); // 用于登录服务器, 注册用户

    public static void main(String[] args) {
        new MainView().mainMenu();
        System.out.println("客户端退出系统");
    }
    private void mainMenu() {
        while (loop) {
            System.out.println("============欢迎登录网络通信系统============");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择: ");

            Scanner scanner = new Scanner(System.in);

            String oper = scanner.next();
            switch (oper) {
                case "1":
                    System.out.print("请输入用户名: ");
                    String userId = scanner.next();
                    System.out.print("请输入密码: ");
                    String pwd = scanner.next();
                    // 需要到服务端去验证用户是否合法
                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("============欢迎 " + userId + " 登录!============");
                        UserMenu userMenu = new UserMenu(userId);
                        while (loop) {
                            int val = userMenu.show();
                            if (val == 0) {
                                loop = false;
                            } else if (val == 2) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("登录服务器失败, 请检查用户名和密码是否有误");
                    }
                    break;
                case "9":
                    loop = false;
                    System.out.println("退出系统");
                    break;
                default:
                    System.out.println("输入信息有误, 请重新输入");
                    break;
            }
        }
    }
}
