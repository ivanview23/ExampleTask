package org.example.part4.decorator;

import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.List;

public abstract class UserManagerDecorator implements UserManager {
    protected UserManager userManager;

    public UserManagerDecorator(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public List<User> getUsers() {
        return userManager.getUsers();
    }

    @Override
    public String getUserInfo(String username) {
        return userManager.getUserInfo(username);
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        userManager.addUser(username, password, userInfo);
    }

    @Override
    public boolean deleteUser(String username) {
        return userManager.deleteUser(username);
    }
}