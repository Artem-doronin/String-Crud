package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Service {

    private final Repository repo;


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
            InMemoryRepository memoryRepository = (InMemoryRepository) repo;
            memoryRepository.saveToStorage();
            System.out.println("✓ Данные сохранены в файл");
        } else {
            System.out.println("✓ Данные уже в БД, сохранение не требуется");
        }
    }
}
