package org.example.part4.chainOfResponsibility.hendler;

import org.example.part4.chainOfResponsibility.UserRequest;
import org.example.part4.user.User;

public class AuthorizationHandler extends UserHandler {

    @Override
    public boolean handle(UserRequest request) {
        System.out.println("AuthorizationHandler: Проверка прав доступа...");

        User currentUser = request.getCurrentUser();

        if (currentUser == null) {
            request.reject("Требуется аутентификация для создания пользователей");
            return false;
        }

        boolean isAdmin = "admin".equals(currentUser.getName()) ||
                "Администратор системы".equals(currentUser.getUserInfo());

        if (!isAdmin) {
            request.reject("Только администраторы могут создавать новых пользователей");
            return false;
        }

        System.out.println("AuthorizationHandler: Права доступа подтверждены");
        return handleNext(request);
    }
}