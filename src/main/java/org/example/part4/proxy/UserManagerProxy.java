package org.example.part4.proxy;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.List;

public class UserManagerProxy implements UserManager {
    private RealUserManager realUserManager;
    private String currentUser;
    private boolean isAuthenticated;

    public UserManagerProxy() {
        this.isAuthenticated = false;

    }

    public boolean authenticate(String username, String password) {
        if (realUserManager == null) {
            realUserManager = new RealUserManager();
        }

        if (realUserManager.validatePassword(username, password)) {
            this.currentUser = username;
            this.isAuthenticated = true;
            System.out.println("Proxy: Успешная аутентификация пользователя " + username);
            return true;
        } else {
            this.isAuthenticated = false;
            System.out.println("Proxy: Ошибка аутентификации для пользователя " + username);
            return false;
        }
    }

    public void logout() {
        this.isAuthenticated = false;
        this.currentUser = null;
        System.out.println("Proxy: Пользователь вышел из системы");
    }

    private void checkAccess() {
        if (!isAuthenticated) {
            throw new SecurityException("Proxy: Доступ запрещен. Требуется аутентификация.");
        }
    }

    private void checkAdminAccess() {
        checkAccess();
        if (!"admin".equals(currentUser)) {
            throw new SecurityException("Proxy: Операция доступна только администратору.");
        }
    }

    @Override
    public List<User> getUsers() {
        checkAccess();
        System.out.println("Proxy: Запрос списка пользователей от " + currentUser);
        return realUserManager.getUsers();
    }

    @Override
    public String getUserInfo(String username) {
        checkAccess();
        System.out.println("Proxy: Запрос информации о " + username + " от " + currentUser);
        return realUserManager.getUserInfo(username);
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        checkAdminAccess();
        System.out.println("Proxy: Добавление пользователя " + username + " администратором " + currentUser);
        realUserManager.addUser(username, password, userInfo);
    }

    @Override
    public boolean deleteUser(String username) {
        checkAdminAccess();
        System.out.println("Proxy: Удаление пользователя " + username + " администратором " + currentUser);
        return realUserManager.deleteUser(username);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}