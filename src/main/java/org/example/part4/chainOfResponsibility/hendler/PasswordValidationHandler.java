package org.example.part4.chainOfResponsibility.hendler;

import org.example.part4.chainOfResponsibility.UserRequest;

public class PasswordValidationHandler extends UserHandler {

    @Override
    public boolean handle(UserRequest request) {
        System.out.println("PasswordValidationHandler: Проверка сложности пароля...");

        String password = request.getPassword();

        if (password.length() < 6) {
            request.reject("Пароль должен содержать минимум 6 символов");
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            request.reject("Пароль должен содержать хотя бы одну цифру");
            return false;
        }

        if (!password.matches(".*[a-zA-Z].*")) {
            request.reject("Пароль должен содержать хотя бы одну букву");
            return false;
        }

        System.out.println("PasswordValidationHandler: Пароль соответствует требованиям");
        return handleNext(request);
    }
}