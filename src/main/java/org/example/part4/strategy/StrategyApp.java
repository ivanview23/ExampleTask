package org.example.part4.strategy;

import org.example.part4.strategy.sorting.IdSortingStrategy;
import org.example.part4.strategy.sorting.NameSortingStrategy;
import org.example.part4.strategy.sorting.UserSortingStrategy;
import org.example.part4.strategy.validate.PasswordValidationStrategy;
import org.example.part4.strategy.validate.SimplePasswordValidation;
import org.example.part4.strategy.validate.StrongPasswordValidation;
import org.example.part4.user.User;

import java.util.Arrays;
import java.util.List;

public class StrategyApp {
    public static void main(String[] args) {

        UserManagementContext context = getUserManagementContext();

        System.out.println("\n=== Тестирование стратегий сортировки ===");

        testSortingStrategy(context, new NameSortingStrategy());
        testSortingStrategy(context, new IdSortingStrategy());

        System.out.println("\n=== Тестирование стратегий валидации ===");

        testValidationStrategy(context, new SimplePasswordValidation(), "123");
        testValidationStrategy(context, new SimplePasswordValidation(), "12");
        testValidationStrategy(context, new StrongPasswordValidation(), "Simple123");
        testValidationStrategy(context, new StrongPasswordValidation(), "StrongPass123!");
    }

    private static UserManagementContext getUserManagementContext() {
        UserManagementContext context = new UserManagementContext();

        User user1 = new User("ivan", "pass1");
        user1.setId(3);
        user1.setUserInfo("Менеджер");

        User user2 = new User("alex", "simple");
        user2.setId(1);
        user2.setUserInfo("Разработчик");

        User user3 = new User("maria", "StrongPass123!");
        user3.setId(2);
        user3.setUserInfo("Аналитик");

        List<User> testUsers = Arrays.asList(user1, user2, user3);
        context.addUsers(testUsers);

        context.showCurrentStrategies();
        return context;
    }

    private static void testSortingStrategy(UserManagementContext context, UserSortingStrategy strategy) {
        context.setSortingStrategy(strategy);
        List<User> sortedUsers = context.getSortedUsers();

        System.out.println("\n" + strategy.getStrategyName() + ":");
        sortedUsers.forEach(user ->
                System.out.println("  - " + user.getName() + " (ID: " + user.getId() + ")"));
    }

    private static void testValidationStrategy(UserManagementContext context,
                                               PasswordValidationStrategy strategy,
                                               String password) {
        context.setValidationStrategy(strategy);
        System.out.println("\nПроверка пароля: '" + password + "'");
        System.out.println("Требования: " + strategy.getRequirements());
        context.validatePassword(password);
    }
}

