package org.example.part4.chainOfResponsibility;

import org.example.part4.chainOfResponsibility.hendler.AuthorizationHandler;
import org.example.part4.chainOfResponsibility.hendler.PasswordValidationHandler;
import org.example.part4.chainOfResponsibility.hendler.UserHandler;
import org.example.part4.user.RealUserManager;

public class UserRegistrationService {
    private UserHandler chain;
    private RealUserManager userManager;

    public UserRegistrationService(RealUserManager userManager) {
        this.userManager = userManager;
        buildChain();
    }

    private void buildChain() {
        PasswordValidationHandler passwordValidation = new PasswordValidationHandler();
        AuthorizationHandler authorization = new AuthorizationHandler();

        this.chain = passwordValidation;
        passwordValidation.setNext(authorization);
    }

    public RegistrationResult registerUser(UserRequest request) {
        System.out.println("Начало обработки запроса на регистрацию: " + request);

        boolean success = chain.handle(request);

        if (success) {
            userManager.addUser(request.getUsername(), request.getPassword(), request.getUserInfo());
            request.setApproved(true);
            System.out.println("ПОЛЬЗОВАТЕЛЬ УСПЕШНО СОЗДАН: " + request.getUsername());
            return new RegistrationResult(true, "Пользователь успешно создан", request.getUsername());
        } else {
            System.out.println("ОШИБКА РЕГИСТРАЦИИ: " + request.getRejectionReason());
            return new RegistrationResult(false, request.getRejectionReason(), null);
        }
    }

    public static class RegistrationResult {
        private final boolean success;
        private final String message;
        private final String username;

        public RegistrationResult(boolean success, String message, String username) {
            this.success = success;
            this.message = message;
            this.username = username;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public String getUsername() { return username; }
    }

    public void setCustomChain(UserHandler customChain) {
        this.chain = customChain;
    }
}