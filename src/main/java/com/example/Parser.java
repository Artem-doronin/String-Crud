package com.example;

import java.util.Arrays;

public class Parser {
    private final Validator validator;

    public Parser(Validator validator) {
        this.validator = validator;
    }

    public Command parse(String input) {

        validator.isEmpty(input);

        String[] tokens = input.trim().split("\\s+");
        String command = tokens[0].toUpperCase();

        validator.isValidCommand(command);

        try {
            return parseCommand(tokens, command);
        } catch (Exception e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            return null;
        }
    }

    private Command parseCommand(String[] tokens, String command) {
        return switch (command) {
            case "CREATE" -> parseCreateCommand(tokens);
            case "GET" -> parseGetCommand(tokens);
            case "UPDATE" -> parseUpdateCommand(tokens, command);
            case "DELETE" -> parseDeleteCommand(tokens, command);
            default -> null;
        };
    }

    private Command parseCreateCommand(String[] tokens) {
        String text = validator.validateCreateCommand(tokens);
        return new Command(null, false, "CREATE", text);
    }

    private Command parseGetCommand(String[] tokens) {
        Long id = null;
        if (tokens.length > 1) {
            validator.isValidId(tokens[1]);
            id = Long.parseLong(tokens[1]);
        }

        return new Command(id, tokens.length > 1, "GET", "");
    }

    private Command parseUpdateCommand(String[] tokens, String command) {
        validator.validateUpdateCommand(tokens);
        Long id = Long.parseLong(tokens[1]);
        String value = tokens.length > 2 ?
                String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length)) :
                "";
        return new Command(id, true, command, value);
    }

    private Command parseDeleteCommand(String[] tokens, String command) {
        validator.validateDeleteCommand(tokens);
        Long id = Long.parseLong(tokens[1]);
        String value = tokens.length > 2 ?
                String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length)) :
                "";
        return new Command(id, true, command, value);
    }
}
