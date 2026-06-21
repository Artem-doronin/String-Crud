package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

public class Parser {
    private final Validator validator;
    private final PersonMapper mapper;

    public Parser(Validator validator) {
        this.validator = validator;
        this.mapper = new PersonMapper();
    }

    public Command parse(String input) throws JsonProcessingException {

        validator.isEmpty(input);

        String[] tokens = input.trim().split("\\s+");
        String command = tokens[0].toUpperCase();

        validator.isValidCommand(command);
            return parseCommand(tokens, command);
    }

    private Command parseCommand(String[] tokens, String command) throws JsonProcessingException {
        return switch (command) {
            case "CREATE" -> parseCreateCommand(tokens);
            case "GET" -> parseGetCommand(tokens);
            case "UPDATE" -> parseUpdateCommand(tokens, command);
            case "DELETE" -> parseDeleteCommand(tokens, command);
            default -> null;
        };
    }

    private Command parseCreateCommand(String[] tokens) throws JsonProcessingException {
        String text = validator.validateCreateCommand(tokens);
        return new Command(null, false, "CREATE", mapper.jsonToPerson(text));
    }

    private Command parseGetCommand(String[] tokens) {
        Long id = null;
        if (tokens.length > 1) {
            validator.isValidId(tokens[1]);
            id = Long.parseLong(tokens[1]);
        }

        return new Command(id, tokens.length > 1, "GET", null);
    }

    private Command parseUpdateCommand(String[] tokens, String command) throws JsonProcessingException {
        validator.validateUpdateCommand(tokens);
        Long id = Long.parseLong(tokens[1]);
        String value = tokens.length > 2 ?
                String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length)) :
                "";
        return new Command(id, true, command, mapper.jsonToPerson(value));
    }

    private Command parseDeleteCommand(String[] tokens, String command) {
        validator.validateDeleteCommand(tokens);
        Long id = Long.parseLong(tokens[1]);
        String value = tokens.length > 2 ?
                String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length)) :
                "";
        return new Command(id, true, command, null);
    }
}
