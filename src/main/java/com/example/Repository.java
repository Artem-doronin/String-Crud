package com.example;

public class Repository {

    private final LoudStorage storage;

    public Repository(LoudStorage storage) {
        this.storage = storage;
    }

    public void create(Command command) {
        command.setId(storage.generateId());
        storage.getMap().put(command.getId(), command.getValue());
        System.out.println("String saved with id = {" + command.getId() + "}");
    }

    public void updateToId(Command command) {
        storage.getMap().put(command.getId(), command.getValue());
        System.out.println("String  with id = {" + command.getId() + "} updated");
    }

    public void getAll() {
        for (String s : storage.getMap().values()) {
            System.out.println(s);
        }
    }

    public void getToId(Command command) {
        System.out.println(storage.getMap().get(command.getId()));
    }

    public void deleteToId(Command command) {
        System.out.println("String with id = " + command.getId() + " deleted");
    }

}
