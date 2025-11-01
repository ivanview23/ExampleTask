package org.example.part4.decorator;

import org.example.part4.user.UserManager;

public class ValidationUserManagerDecorator extends UserManagerDecorator {

    public ValidationUserManagerDecorator(UserManager userManager) {
        super(userManager);
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        System.out.println("VALIDATION: Проверка данных нового пользователя...");

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        if (password == null || password.length() < 3) {
            throw new IllegalArgumentException("Пароль должен содержать минимум 3 символа");
        }

        if (userInfo == null || userInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Информация о пользователе не может быть пустой");
        }

        System.out.println("VALIDATION: Данные прошли проверку");
        super.addUser(username, password, userInfo);
    }

    @Override
    public String getUserInfo(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        return super.getUserInfo(username);
    }

    @Override
    public boolean deleteUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        if ("admin".equals(username)) {
            throw new SecurityException("Нельзя удалить администратора системы");
        }

        return super.deleteUser(username);
    }
}