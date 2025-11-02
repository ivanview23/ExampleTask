package org.example.part4.builder;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;

public class BuilderApp {
    public static void main(String[] args) {

        System.out.println("=== Простой UserBuilder ===");
        User testUser = UserBuilder.create("Alex", "password123")
                .withUserInfo("Обычный пользователь")
                .withId(1001L)
                .build();
        System.out.println("Создан: " + testUser);

        System.out.println("\n=== Разные варианты создания пользователей ===");

        User minimalUser = UserBuilder.create("minUser", "pass123").build();
        System.out.println("Минимальный: " + minimalUser);

        User autoIdUser = UserBuilder.create("auto_idUser", "autoPass")
                .withUserInfo("Авто-ID пользователь")
                .build();
        System.out.println("Авто-ID: " + autoIdUser);

        User fullUser = UserBuilder.create("fullUser", "FullPass123!")
                .withId(2001L)
                .withUserInfo("Пользователь с полной информацией и кастомным ID")
                .build();
        System.out.println("Полный: " + fullUser);

        System.out.println("\n=== UserManagerBuilder - разные сценарии ===");

        RealUserManager emptyManager = UserManagerBuilder.create().build();
        System.out.println("Пустой менеджер: " + emptyManager.getUsers().size() + " пользователей");

        RealUserManager singleUserManager = UserManagerBuilder.create()
                .addUser("Sam", "soloPass", "Единственный пользователь")
                .build();
        System.out.println("Один пользователь: " + singleUserManager.getUsers().size() + " пользователей");

        RealUserManager multiUserManager = UserManagerBuilder.create()
                .addUser(testUser)
                .addUser("Jon", "secretPass", "Тестировщик")
                .addUser(UserBuilder.create("Charlie", "charPass")
                        .withUserInfo("Дизайнер")
                        .build())
                .addUser("Alice", "alice123", "Аналитик")
                .addUser("Bob", "bob456", "Менеджер")
                .build();

        System.out.println("Мульти-менеджер: " + multiUserManager.getUsers().size() + " пользователей");
        multiUserManager.getUsers().forEach(u ->
                System.out.println("  - " + u.getName() + ": " + u.getUserInfo() + " (ID: " + u.getId() + ")"));

        System.out.println("\n=== Проверка функциональности менеджера ===");

        System.out.println("Поиск информации о пользователе 'Jon': " +
                multiUserManager.getUserInfo("Jon"));

        System.out.println("Проверка пароля 'Jon': " +
                multiUserManager.validatePassword("Jon", "secretPass"));

        System.out.println("Проверка неверного пароля 'Jon': " +
                multiUserManager.validatePassword("Jon", "wrongPass"));

        System.out.println("\n=== Цепочка вызовов (Fluent Interface) ===");

        RealUserManager fluentManager = UserManagerBuilder.create()
                .addUser(UserBuilder.create("fluent1", "pass1")
                        .withUserInfo("Первый fluent пользователь")
                        .withId(3001L)
                        .build())
                .addUser("fluent2", "pass2", "Второй пользователь")
                .addUser(UserBuilder.create("fluent3", "pass3")
                        .withId(3002L)
                        .build())
                .addUser("fluent4", "pass4", "Четвертый пользователь")
                .build();

        System.out.println("Fluent менеджер создан: " + fluentManager.getUsers().size() + " пользователей стало");

    }
}