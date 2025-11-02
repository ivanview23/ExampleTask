package org.example.part4.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealUserManager implements UserManager {
    private final Map<String, User> userData;

    public RealUserManager() {
        userData = new HashMap<>();

        User admin = new User("admin", "Secret1");
        admin.setUserInfo("Администратор системы");
        admin.setId(1L);

        User dima = new User("Dima", "Secret2");
        dima.setUserInfo("Обычный пользователь");
        dima.setId(2L);

        User john = new User("John", "Secret3");
        john.setUserInfo("John Doe - разработчик");
        john.setId(3L);

        userData.put("admin", admin);
        userData.put("user1", dima);
        userData.put("john", john);
    }

    @Override
    public List<User> getUsers() {
        System.out.println("RealUserManager: Получение списка всех пользователей");
        return new ArrayList<>(userData.values());
    }

    @Override
    public String getUserInfo(String username) {
        System.out.println("RealUserManager: Получение информации о пользователе " + username);
        User user = userData.get(username);
        return user != null ? user.getUserInfo() : "Пользователь не найден";
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        System.out.println("RealUserManager: Добавление нового пользователя " + username);
        User newUser = new User(username, password);
        newUser.setUserInfo(userInfo);
        newUser.setId(System.currentTimeMillis());
        userData.put(username, newUser);
    }

    @Override
    public boolean deleteUser(String username) {
        System.out.println("RealUserManager: Удаление пользователя " + username);
        return userData.remove(username) != null;
    }

    public boolean validatePassword(String username, String password) {
        User user = userData.get(username);
        return user != null && user.getPassword().equals(password);
    }
}