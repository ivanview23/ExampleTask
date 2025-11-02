package org.example.part4.chainOfResponsibility.hendler;

import org.example.part4.chainOfResponsibility.UserRequest;

public abstract class UserHandler {
    protected UserHandler nextHandler;

    public UserHandler setNext(UserHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public abstract boolean handle(UserRequest request);

    protected boolean handleNext(UserRequest request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return true;
    }
}