package com.example;

public class ExampleValidator implements Validator {
    @Override
    public void isEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new NullPointerException("Строка не может быть пустой");
        }
    }

    @Override
    public void isValidCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            throw new IllegalArgumentException("Команда не может быть пустой");
        }

        String cmd = command.toUpperCase();
        if (!cmd.equals("GET") && !cmd.equals("CREATE") &&
                !cmd.equals("UPDATE") && !cmd.equals("DELETE")) {
            throw new IllegalArgumentException("Неизвестная команда: " + command);
        }
    }

    @Override
    public void isValidId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new NullPointerException("ID не может быть пустым");
        }

        try {
            long parsedId = Long.parseLong(id);
            if (parsedId <= 0) {
                throw new IllegalArgumentException("ID должен быть положительным числом");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID должен быть числом: " + id);
        }
    }

    @Override
    public String validateCreateCommand(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("CREATE требует текст");
        }
        String text = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
        hasValidCreateText(text);
        return text;
    }

    @Override
    public void validateUpdateCommand(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("UPDATE требует ID");
        }
        isValidId(parts[1]);
        if (parts.length < 3) {
            throw new IllegalArgumentException("UPDATE требует текст");
        }
        String text = String.join(" ", java.util.Arrays.copyOfRange(parts, 2, parts.length));
        hasValidUpdateText(text);
    }

    @Override
    public void validateDeleteCommand(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("DELETE требует ID");
        }
        isValidId(parts[1]);
    }

    // Дополнительные методы
    private void hasValidUpdateText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("UPDATE требует непустое значение");
        }
        if (text.length() > 1000) {
            throw new IllegalArgumentException("Текст слишком длинный (максимум 1000 символов)");
        }
    }

    private void hasValidCreateText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("CREATE требует непустое значение");
        }
        if (text.length() > 1000) {
            throw new IllegalArgumentException("Текст слишком длинный (максимум 1000 символов)");
        }
    }
}
