package org.example.part4.strategy.validate;

public interface PasswordValidationStrategy {
    boolean validate(String password);
    String getRequirements();
}
