package com.example;

public class ExampleValidator implements Validator {
    @Override
    public boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    @Override
    public boolean isValidCommand(String command) {
        return "GET".equals(command) || "CREATE".equals(command)
                || "UPDATE".equals(command) || "DELETE".equals(command);
    }

    @Override
    public boolean isValidId(String id) {
        if (id == null || id.trim().isEmpty()) return false;
        try {
            Long.parseLong(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean hasValidCreateText(String text) {
        return !isEmpty(text);
    }

    @Override
    public boolean isGetCommandWithId(String input) {
        if (isEmpty(input)) return false;
        String[] parts = input.trim().split("\\s+");
        return parts.length > 1 && isValidId(parts[1]);
    }
}
