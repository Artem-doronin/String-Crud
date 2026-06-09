package com.example;

public interface Validator {
    boolean isEmpty(String input);
    boolean isValidCommand(String command);
    boolean isValidId(String id);
    boolean hasValidCreateText(String text);
    boolean isGetCommandWithId(String input);
}


