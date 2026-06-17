package com.example;

import java.util.List;

public class Service {

    private final Repository repo;
    private final LoudStorage storage;

    public Service(Repository repo, LoudStorage storage) {
        this.repo = repo;
        this.storage = storage;
    }

    public void create(Command command) {
        repo.create(command);
    }

    public void updateToId(Command command) {
        repo.updateToId(command);
    }

    public void deleteToId(Command command) {
        repo.deleteToId(command);
    }

    public void getToId(Command command) {
        System.out.println(repo.getToId(command));
    }

    public void getAll() {
        List<Person> persons = repo.getAll();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public void saveMapBySerialization() {
        storage.saveMapToFile(repo.getMap());
    }
}
