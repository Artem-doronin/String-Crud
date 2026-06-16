package com.example;

public interface Validator {
    void isEmpty(String input);
    void isValidCommand(String command);
    void isValidId(String id);
    String validateCreateCommand(String[] parts);
    void validateUpdateCommand(String[] parts);
    void validateDeleteCommand(String[] parts);
}


