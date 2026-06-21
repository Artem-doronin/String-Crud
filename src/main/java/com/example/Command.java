package com.example;

public class Command {

    private Long id;
    private Boolean availabilityOfIdInRequest;
    private String command;
    private Person value;

    public Command(Long id,Boolean availabilityOfIdInGet, String command, Person value) {
        this.id = id;
        this.availabilityOfIdInRequest = availabilityOfIdInGet;
        this.command = command;
        this.value = value;
    }
    public Boolean getAvailabilityOfIdInRequest() {
        return availabilityOfIdInRequest;
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
