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

    public void setAvailabilityOfIdInGet(Boolean availabilityOfIdInGet) {
        this.availabilityOfIdInRequest = availabilityOfIdInGet;
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

    public Person getValue() {
        return value;
    }

    public void setValue(Person value) {
        this.value = value;
    }
}
