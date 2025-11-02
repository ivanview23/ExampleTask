package org.example.part4.strategy;

import org.example.part4.strategy.sorting.NameSortingStrategy;
import org.example.part4.strategy.sorting.UserSortingStrategy;
import org.example.part4.strategy.validate.PasswordValidationStrategy;
import org.example.part4.strategy.validate.SimplePasswordValidation;
import org.example.part4.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagementContext {
    private UserSortingStrategy sortingStrategy;
    private PasswordValidationStrategy validationStrategy;
    private List<User> users;

    public UserManagementContext() {
        this.users = new ArrayList<>();
        this.sortingStrategy = new NameSortingStrategy();
        this.validationStrategy = new SimplePasswordValidation();
    }

    public void setSortingStrategy(UserSortingStrategy strategy) {
        this.sortingStrategy = strategy;
        System.out.println("Установлена стратегия сортировки: " + strategy.getStrategyName());
    }

    public void setValidationStrategy(PasswordValidationStrategy strategy) {
        this.validationStrategy = strategy;
        System.out.println("\nУстановлена стратегия валидации: " + strategy.getRequirements());
    }

    public List<User> getSortedUsers() {
        System.out.println("Применение стратегии сортировки: " + sortingStrategy.getStrategyName());
        return sortingStrategy.sort(new ArrayList<>(users));
    }

    public boolean validatePassword(String password) {
        System.out.println("Валидация пароля по стратегии...");
        boolean isValid = validationStrategy.validate(password);
        System.out.println("Результат валидации: " + (isValid ? "ACCESS" : "FAILED"));
        return isValid;
    }

    public void addUser(User user) {
        if (validatePassword(user.getPassword())) {
            users.add(user);
            System.out.println("Пользователь " + user.getName() + " добавлен");
        } else {
            System.out.println("Не удалось добавить пользователя: неверный пароль");
        }
    }

    public void addUsers(List<User> userList) {
        users.addAll(userList);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void showCurrentStrategies() {
        System.out.println("\n=== ТЕКУЩИЕ СТРАТЕГИИ ===");
        System.out.println("Сортировка: " + sortingStrategy.getStrategyName());
        System.out.println("Валидация: " + validationStrategy.getRequirements());
    }
}