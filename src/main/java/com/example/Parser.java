package com.example;

import java.util.Arrays;

public class Parser {
    private final Validator validator;

    public Parser(Validator validator) {
        this.validator = validator;
    }

    public Command parse(String input) {
        if (validator.isEmpty(input)) {
            System.err.println("Ошибка: пустая команда");
            return null;
        }

        String[] tokens = input.trim().split("\\s+");
        String command = tokens[0].toUpperCase();

        if (!validator.isValidCommand(command)) {
            System.err.println("Ошибка: неизвестная команда '" + command + "'");
            return null;
        }

        try {
            return parseCommand(tokens, command);
        } catch (Exception e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            return null;
        }
    }

    private Command parseCommand(String[] tokens, String command) {
        switch (command) {
            case "CREATE":
                return parseCreateCommand(tokens);
            case "GET":
                return parseGetCommand(tokens);
            case "UPDATE":
            case "DELETE":
                return parseUpdateOrDeleteCommand(tokens, command);
            default:
                return null;
        }
    }

    private Command parseCreateCommand(String[] tokens) {
        if (tokens.length < 2) {
            System.err.println("Ошибка: CREATE требует текст");
            return null;
        }

        String text = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
        if (!validator.hasValidCreateText(text)) {
            System.err.println("Ошибка: CREATE требует непустое значение");
            return null;
        }

        return new Command(null, false, "CREATE", text);
    }

    private Command parseGetCommand(String[] tokens) {
        Long id = null;
        if (tokens.length > 1) {
            if (!validator.isValidId(tokens[1])) {
                System.err.println("Ошибка: ID должен быть числом");
                return null;
            }
            id = Long.parseLong(tokens[1]);
        }

        return new Command(id, tokens.length > 1, "GET", "");
    }

    private Command parseUpdateOrDeleteCommand(String[] tokens, String command) {
        if (tokens.length < 2) {
            System.err.println("Ошибка: " + command + " требует ID");
            return null;
        }

        String idStr = tokens[1];
        if (!validator.isValidId(idStr)) {
            System.err.println("Ошибка: ID должен быть числом");
            return null;
        }

        Long id = Long.parseLong(idStr);
        String value = tokens.length > 2 ?
                String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length)) :
                "";

        return new Command(id, true, command, value);
    }
}
