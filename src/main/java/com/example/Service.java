package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Service {

    private final RepositoryDb repo;
    private final LoudStorage storage;

    public Service(RepositoryDb repo, LoudStorage storage) {
        this.repo = repo;
        this.storage = storage;
    }

    public void create(Command command) {
        repo.create(command);
    }

    public void updateById(Command command) {
        repo.updateById(command);
    }

    public void deleteById(Command command) {
        repo.deleteById(command);
    }

    public void getById(Command command) {
        System.out.println(repo.getById(command));
    }

    public void getAll() {
        List<Person> persons = repo.getAll();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public void saveMapBySerialization() throws JsonProcessingException {
        storage.saveMapToFile(repo.getMap());
    }
}
