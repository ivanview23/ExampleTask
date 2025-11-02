package org.example.part4.adapter;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.List;

public class AdapterApp {
    public static void main(String[] args) {
        UserManager nativeSystem = new RealUserManager();
        testUserManager(nativeSystem, "Родная система");

        ExternalUserService externalService = new ExternalUserServiceImpl();
        ExternalServiceAdapter externalAdapter = new ExternalServiceAdapter(externalService);
        testUserManager(externalAdapter, "Внешняя система через Adapter");

        System.out.println("\n=== Дополнительные методы адаптера ===");
        System.out.println("Количество пользователей во внешней системе: " + externalAdapter.getExternalUserCount());
        System.out.println("Существует ли пользователь 'admin': " + externalAdapter.isUserExistsInExternalSystem("admin"));
        System.out.println("Существует ли пользователь 'unknown': " + externalAdapter.isUserExistsInExternalSystem("unknown"));
    }

    private static void testUserManager(UserManager manager, String systemName) {
        System.out.println("\n=== Тестирование: " + systemName + " ===");

        try {
            List<User> users = manager.getUsers();
            System.out.println(systemName + " - пользователей: " + users.size());

            String info = manager.getUserInfo("admin");
            System.out.println(systemName + " - информация об admin: " + info);

            manager.addUser("newUser", "newPass", "Новый тестовый пользователь");

            boolean deleted = manager.deleteUser("newUser");
            System.out.println(systemName + " - пользователь удален: " + deleted);

        } catch (Exception e) {
            System.out.println(systemName + " - ошибка: " + e.getMessage());
        }
    }
}