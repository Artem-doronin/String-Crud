package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Service {

    private final Repository repo;


    public Service(Repository repo) {
        this.repo = repo;
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
    //todo буду колхозить


    public void exitToSave() throws JsonProcessingException {
        if (repo instanceof InMemoryRepository) {
            InMemoryRepository memoryRepository = (InMemoryRepository) repo;
            memoryRepository.saveToStorage();
            System.out.println("✓ Данные сохранены в файл");
        } else {
            System.out.println("✓ Данные уже в БД, сохранение не требуется");
        }
    }
}
