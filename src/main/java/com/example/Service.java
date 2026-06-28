package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Service {

    private final Repository repo;
    private final DataLoader dataLoader = new DataLoader();


    public Service(Repository repo) {
        this.repo = repo;
    }

    public void create(Person person) {
        repo.create(person);
    }

    public void updateById(Person person) {
        repo.updateById(person);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void getById(Long id) {
        System.out.println(repo.getById(id));
    }

    public void getAll() {
        List<Person> persons = repo.getAll();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public void exitToSave() throws JsonProcessingException {
        if (repo instanceof InMemoryRepository) {
            dataLoader.saveData(repo.getAll());
        }
    }
}
