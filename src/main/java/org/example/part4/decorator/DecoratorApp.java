package org.example.part4.decorator;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.List;

public class DecoratorApp {

    public static void main(String[] args) {
        System.out.println("=== Базовый функционал ===");
        UserManager basicManager = new RealUserManager();
        testUserManager(basicManager);

        System.out.println("\n=== Логирование ===");
        UserManager loggedManager = new LoggingUserManagerDecorator(new RealUserManager());
        testUserManager(loggedManager);

        System.out.println("\n=== Логирование + Кэширование ===");
        UserManager cachedAndLoggedManager = new CachingUserManagerDecorator(
                new LoggingUserManagerDecorator(new RealUserManager())
        );
        testUserManager(cachedAndLoggedManager);

        System.out.println("\n=== Логирование + Кэширование + Валидация ===");
        UserManager fullDecoratorManager = new ValidationUserManagerDecorator(
                new CachingUserManagerDecorator(
                        new LoggingUserManagerDecorator(new RealUserManager())
                )
        );
        testUserManager(fullDecoratorManager);

    }

    private static void testUserManager(UserManager manager) {
        List<User> users = manager.getUsers();
        System.out.println("Найдено пользователей: " + users.size());

        String info = manager.getUserInfo("admin");
        System.out.println("Информация об admin: " + info);

        manager.addUser("testUser", "testPass", "Тестовый пользователь");

        manager.deleteUser("testUser");
    }
}