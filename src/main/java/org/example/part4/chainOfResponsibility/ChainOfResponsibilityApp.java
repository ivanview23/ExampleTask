package org.example.part4.chainOfResponsibility;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;

public class ChainOfResponsibilityApp {

    public static void main(String[] args) {
        RealUserManager userManager = new RealUserManager();

        UserRegistrationService registrationService = new UserRegistrationService(userManager);

        User admin = userManager.getUsers().stream()
                .filter(u -> "admin".equals(u.getName()))
                .findFirst()
                .orElseThrow();

        System.out.println("=== Успешное создание пользователя ===");
        UserRequest successRequest = new UserRequest(
                "newuser",
                "Password123",
                "Новый тестовый пользователь",
                admin
        );

        UserRegistrationService.RegistrationResult result = registrationService.registerUser(successRequest);
        printResult(result);

        System.out.println("\n=== Слабый пароль ===");
        UserRequest weakPasswordRequest = new UserRequest(
                "anotheruser",
                "weak",
                "Пользователь со слабым паролем",
                admin
        );

        result = registrationService.registerUser(weakPasswordRequest);
        printResult(result);

        System.out.println("\n=== Пользователь без прав администратора ===");
        User regularUser = new User("regular", "RegularPass123");
        regularUser.setUserInfo("Regular User");

        UserRequest noPermissionRequest = new UserRequest(
                "someuser",
                "SomePass123",
                "Обычный пользователь",
                regularUser
        );

        result = registrationService.registerUser(noPermissionRequest);
        printResult(result);

        userManager.getUsers().forEach(user ->
                System.out.println("  - " + user.getName() + " : " + user.getUserInfo()));
    }

    private static void printResult(UserRegistrationService.RegistrationResult result) {
        if (result.isSuccess()) {
            System.out.println("ACCESS: " + result.getMessage());
        } else {
            System.out.println("FAILED: " + result.getMessage());
        }
    }
}