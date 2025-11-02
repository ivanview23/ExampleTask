package org.example.part4.strategy.validate;

public class SimplePasswordValidation implements PasswordValidationStrategy {
    @Override
    public boolean validate(String password) {
        return password != null && password.length() >= 3;
    }

    @Override
    public String getRequirements() {
        return "Простой пароль, минимум 3 символа";
    }
}