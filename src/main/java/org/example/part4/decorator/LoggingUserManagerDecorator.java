package org.example.part4.decorator;

import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.List;

public class LoggingUserManagerDecorator extends UserManagerDecorator {

    public LoggingUserManagerDecorator(UserManager userManager) {
        super(userManager);
    }

    @Override
    public List<User> getUsers() {
        System.out.println("LOGGING: Запрос списка пользователей в " + new java.util.Date());
        long startTime = System.currentTimeMillis();

        List<User> result = super.getUsers();

        long endTime = System.currentTimeMillis();
        System.out.println("LOGGING: Запрос выполнен за " + (endTime - startTime) + "ms");
        return result;
    }

    @Override
    public String getUserInfo(String username) {
        System.out.println("LOGGING: Запрос информации о пользователе '" + username + "'");

        String result = super.getUserInfo(username);

        System.out.println("LOGGING: Результат: " + result);
        return result;
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        System.out.println("LOGGING: Попытка добавления пользователя '" + username + "'");

        super.addUser(username, password, userInfo);

        System.out.println("LOGGING: Пользователь '" + username + "' успешно добавлен");
    }

    @Override
    public boolean deleteUser(String username) {
        System.out.println("LOGGING: Попытка удаления пользователя '" + username + "'");

        boolean result = super.deleteUser(username);

        System.out.println("LOGGING: Результат удаления: " + (result ? "ACCESS" : "FAILS"));
        return result;
    }
}