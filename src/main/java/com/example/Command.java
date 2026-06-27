package com.example;

public class Command {

    private Long id;
    private String command;
    private Person value;

    public Command(Long id, String command, Person value) {
        this.id = id;
        this.command = command;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public Person getValue() {
        return value;
    }
}
