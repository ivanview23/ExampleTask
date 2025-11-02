package org.example.part4.adapter;

import java.util.HashMap;
import java.util.Map;

public class ExternalUserServiceImpl implements ExternalUserService {
    private final Map<String, ExternalUser> externalUsers;

    public ExternalUserServiceImpl() {
        externalUsers = new HashMap<>();
        externalUsers.put("admin", new ExternalUser("admin", "admin_user", "System Administrator", "Secret1"));
        externalUsers.put("user1", new ExternalUser("user1", "dima_user", "Regular User", "Secret2"));
    }

    @Override
    public String getUserDetails(String userLogin) {
        System.out.println("ExternalUserService: Получение деталей пользователя " + userLogin);
        ExternalUser user = externalUsers.get(userLogin);
        return user != null ? user.getProfile() : "User not found in external system";
    }

    @Override
    public void createUser(String login, String credentials, String profile) {
        System.out.println("ExternalUserService: Создание пользователя " + login);
        ExternalUser newUser = new ExternalUser(login, login + "_name", profile, credentials);
        externalUsers.put(login, newUser);
    }

    @Override
    public boolean removeUser(String userLogin) {
        System.out.println("ExternalUserService: Удаление пользователя " + userLogin);
        return externalUsers.remove(userLogin) != null;
    }

    @Override
    public String[] listAllUsers() {
        System.out.println("ExternalUserService: Получение списка всех пользователей");
        return externalUsers.keySet().toArray(new String[0]);
    }
}