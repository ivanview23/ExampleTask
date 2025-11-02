package org.example.part4.adapter;

import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalServiceAdapter implements UserManager {
    private ExternalUserService externalService;

    public ExternalServiceAdapter(ExternalUserService externalService) {
        this.externalService = externalService;
    }

    @Override
    public List<User> getUsers() {
        System.out.println("Adapter: преобразование String[] в List<User>");
        String[] userLogins = externalService.listAllUsers();

        return Arrays.stream(userLogins)
                .map(login -> {
                    User user = new User(login, "unknown"); // Пароль неизвестен
                    user.setUserInfo(externalService.getUserDetails(login));
                    return user;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getUserInfo(String username) {
        System.out.println("Adapter: вызов getUserDetails вместо getUserInfo");
        return externalService.getUserDetails(username);
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        System.out.println("Adapter: вызов createUser вместо addUser");
        externalService.createUser(username, password, userInfo);
    }

    @Override
    public boolean deleteUser(String username) {
        System.out.println("Adapter: вызов removeUser вместо deleteUser");
        return externalService.removeUser(username);
    }

    public int getExternalUserCount() {
        return externalService.listAllUsers().length;
    }

    public boolean isUserExistsInExternalSystem(String username) {
        return !"User not found in external system".equals(externalService.getUserDetails(username));
    }
}