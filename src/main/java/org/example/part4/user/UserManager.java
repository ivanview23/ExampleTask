package org.example.part4.user;

import java.util.List;

public interface UserManager {
    List<User> getUsers();
    String getUserInfo(String username);
    void addUser(String username, String password, String userInfo);
    boolean deleteUser(String username);
}