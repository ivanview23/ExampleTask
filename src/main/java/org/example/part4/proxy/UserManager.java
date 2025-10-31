package org.example.part4.proxy;

import java.util.List;

public interface UserManager {
    List<User> getUsers(); // Изменено: возвращаем список пользователей
    String getUserInfo(String username);
    void addUser(String username, String password, String userInfo); // Добавлен пароль
    boolean deleteUser(String username);
}