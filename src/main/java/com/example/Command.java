package com.example;

public class Command {

    private Long id;
    private String command;
    private String value;

    public Command(Long id, String command, String value) {
        this.id = id;
        this.command = command;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
