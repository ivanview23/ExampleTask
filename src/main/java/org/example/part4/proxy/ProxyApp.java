package org.example.part4.proxy;

import org.example.part4.user.User;

import java.util.List;

public class ProxyApp {

    public static void main(String[] args) {
        UserManagerProxy userManager = new UserManagerProxy();

        System.out.println("=== Попытка доступа без аутентификации ===");
        try {
            userManager.getUsers();
        } catch (SecurityException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Аутентификация обычного пользователя ===");
        boolean authResult = userManager.authenticate("user1", "Secret2");
        System.out.println("Аутентификация: " + authResult);

        System.out.println("\n=== Работа обычного пользователя ===");
        try {
            List<User> users = userManager.getUsers();
            System.out.println("Список пользователей:");
            for (User user : users) {
                System.out.println("  - " + user.getName());
            }

            String info = userManager.getUserInfo("admin");
            System.out.println("Информация об admin: " + info);

            // Попытка добавить пользователя (должна быть ошибка)
            userManager.addUser("newuser", "pass", "Новый пользователь");

        } catch (SecurityException e) {
            System.out.println("Ошибка доступа: " + e.getMessage());
        }

        System.out.println("\n=== Аутентификация администратора ===");
        userManager.logout();
        authResult = userManager.authenticate("admin", "Secret1");
        System.out.println("Аутентификация admin: " + authResult);

        System.out.println("\n=== Работа администратора ===");
        try {
            userManager.addUser("newuser", "newpass123", "Новый тестовый пользователь");

            List<User> users = userManager.getUsers();
            System.out.println("Обновленный список пользователей:");
            for (User user : users) {
                System.out.println("  - " + user.getName() + " (" + user.getUserInfo() + ")");
            }

        } catch (SecurityException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}