package org.example.part4.strategy.validate;

public class StrongPasswordValidation implements PasswordValidationStrategy {

    @Override
    public boolean validate(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecial;
    }

    @Override
    public String getRequirements() {
        return "Пароль должен содержать минимум 8 символов, заглавные и строчные буквы, цифры и специальные символы";
    }
}